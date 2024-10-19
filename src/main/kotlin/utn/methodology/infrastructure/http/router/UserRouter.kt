package utn.methodology.domain.entities.post;

import utn.Application.commands.CreateUserCommand;
import utn.Application.commandhandlers.CreateUserHandler;
import utn.Application.infrastructure.http.actions.CreateUserAction;
import utn.Application.infrastructure.persistence.MongoUserRepository;
import utn.Application.infrastructure.persistence.Databases;
import utn.methodology.infrastructure.http.actions.FindUserByUsernameAction

fun Application.userRouter() {
    val mongoDatabase = Databases();
    
    val userMongoUserRepository = MongoUserRepository(Databases);
    
    val CreateUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository));
    val findUserByUsernameAction = FindUserByUsernameAction(FindUserByUsernameHandler(userMongoUserRepository))
    
    routing {

        post("/users") {
            val body = call.receive<CreateUserCommand>();
            createUserAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"));
        }

        get("/users") {
            val username = call.request.queryParameters["username"]
            if (username.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            else
            {
                val userQuery = SearchUserQuery(
                    uuid = "unknown",
                    nombre = "unknown",
                    username = username,
                    email = "unknown",
                    contraseñia = "unknown"
                )

                FindUserByUsernameHandler.handleSearchUser(userQuery)
            }
        }
    }
}