package utn.methodology.domain.entities.User;

import utn.Application.commands.CreateUserCommand;
import utn.Application.commandhandlers.CreateUserHandler;
import utn.Application.infrastructure.http.actions.CreateUserAction;
import utn.Application.infrastructure.persistence.MongoUserRepository;
import utn.Application.infrastructure.persistence.Databases;

fun Application.userRouter() {
    val mongoDatabase = Databases();
    
    val userMongoUserRepository = MongoUserRepository(Databases);
    
    val CreateUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository));
    
    routing {

        post("/users") {
            val body = call.receive<CreateUserCommand>();
            createUserAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"));
        }
    }
}