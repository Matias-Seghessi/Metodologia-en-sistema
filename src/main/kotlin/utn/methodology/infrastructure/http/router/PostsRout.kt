package utn.methodology.domain.entities.Post

import utn.Application.commands.CreatePostCommand
import utn.Application.commandhandlers.CreatePostHandler
import utn.Application.infrastructure.http.actions.CreatePostAction
//----------------------------------------------
import utn.Application.Commands.DeletePostCommand
import utn.Application.Commandhandlers.DeletePostsHandler
import utn.Application.infrastructure.http.actions.DeletePostAction
//----------------------------------------------
import utn.Application.infrastructure.persistence.MongoPostRepository
import utn.Application.infrastructure.persistence.Databases
import utn.methodology.infrastructure.http.actions.FindUserByUsernameAction
import utn.methodology.infrastructure.http.actions.PostRequest
import utn.methodology.infrastructure.http.actions.Post
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Application.postRout() {
    val mongoDatabase = Databases()
    val postMongoUserRepository = MongoPostRepository(Databases)
    val createPostAction = CreatePostAction(CreatePostHandler(postMongoUserRepository))
    val deletePostAction = DeletePostAction(DeletePostsHandler(postMongoUserRepository))

    routing {

        post("/posts") {
            val postRequest = call.receive<PostRequest>()

            if (postRequest.message.length > 280) {
                call.respond(HttpStatusCode.BadRequest, "El mensaje no puede superar los 280 caracteres.")
                return@post
            }

            val post = Post(userId = postRequest.userId, message = postRequest.message)
            call.respond(HttpStatusCode.Created, post)
        }

        delete("/posts/{id}") {
            val postId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID de post no proporcionado.")
            val query = DeletePostCommand(postId)
            deletePostAction.execute(query)
            call.respond(HttpStatusCode.NoContent)
        }

        get("/posts") {
            val userId = call.request.queryParameters["userId"]?.toIntOrNull()
            val order = call.request.queryParameters["order"] ?: "ASC" 
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10 
            val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0 

            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "El parámetro 'userId' es obligatorio.")
                return@get
            }

            val posts = postMongoUserRepository.getPostsByUser(userId, order, limit, offset)

            if (posts.isEmpty()) {
                call.respond(HttpStatusCode.NoContent, "No se encontraron posts para el usuario con ID $userId.")
            } else {
                call.respond(posts)
            }
        }
    }
}
