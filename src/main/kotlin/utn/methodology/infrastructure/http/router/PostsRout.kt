package utn.methodology.domain.entities.Post;

import utn.Application.commands.CreatePostCommand;
import utn.Application.commandhandlers.CreatePostHandler;
import utn.Application.infrastructure.http.actions.CreatePostAction;
import utn.Application.infrastructure.persistence.MongoPostRepository;
import utn.Application.infrastructure.persistence.Databases;
import utn.methodology.infrastructure.http.actions.FindUserByUsernameAction

fun Application.postRout() {
    val mongoDatabase = Databases();

    val PostMongoUserRepository = MongoPostRepository(Databases);

    val CreatePostAction = CreatePostAction(CreatePostHandler(PostMongoUserRepository));
    val findUserByUsernameAction = FindUserByUsernameAction(FindUserByUsernameHandler(PostMongoUserRepository))
    
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

    }
}