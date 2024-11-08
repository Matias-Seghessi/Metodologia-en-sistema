package utn.methodology.domain.entities.Post;

import utn.Application.commands.CreatePostCommand;
import utn.Application.commandhandlers.CreatePostHandler;
import utn.Application.infrastructure.http.actions.CreatePostAction;
//----------------------------------------------
import utn.Application.Commands.DeletePostCommand;
import utn.Application.Commandhandlers.DeletePostsHandler
import utn.Application.infrastructure.http.actions.DeletePostAction;
//----------------------------------------------
import utn.Application.infrastructure.persistence.MongoPostRepository;
import utn.Application.infrastructure.persistence.Databases;
import utn.methodology.infrastructure.http.actions.FindUserByUsernameAction

fun Application.postRout() {
    val mongoDatabase = Databases();

    val PostMongoUserRepository = MongoPostRepository(Databases);

    val CreatePostAction = CreatePostAction(CreatePostHandler(PostMongoUserRepository));
    val FindPostsByPostsnameAction = FindPostsByPostsnameAction(FindPostsByPostsnameHandler(PostMongoUserRepository))
    val deletePostAction = DeletePostAction(DeletePostsHandler(PostMongoUserRepository))
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

            val query = DeletePostCommand(call.Parameters["id"].toString())

            deletePostAction.execute(query)

            call.respond(HttpStatusCode.NoContent)
        }

    }
}