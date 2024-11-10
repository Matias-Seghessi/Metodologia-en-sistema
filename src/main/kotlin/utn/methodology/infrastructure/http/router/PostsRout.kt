package utn.methodology.domain.entities.Post

import utn.methodology.application.commandhandlers.CreatePostHandler
import utn.methodology.infrastructure.http.actions.CreatePostAction
//----------------------------------------------
import utn.methodology.application.commands.DeletePostCommand
import utn.methodology.application.commandhandlers.DeletePostsHandler
import utn.methodology.infrastructure.http.actions.DeletePostAction
//----------------------------------------------
import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.infrastructure.http.dtos.CreatePostRequestBody
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*

fun Application.postRout() {
    val mongoDatabase = Databases()
    val postMongoUserRepository = MongoPostRepository(Databases)
    val createPostAction = CreatePostAction(CreatePostHandler(postMongoUserRepository))
    val deletePostAction = DeletePostAction(DeletePostsHandler(postMongoUserRepository))

    routing {

        post("/posts") {
            val postRequest = call.receive<CreatePostRequestBody>()

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
