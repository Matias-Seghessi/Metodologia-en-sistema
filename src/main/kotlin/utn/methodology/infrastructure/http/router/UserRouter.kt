package example.com.infrastructure.http.router

import utn.methodology.application.commands.CreateUserCommand
//import example.com.application.commands.UpdateUserCommand
import utn.methodology.application.commandhandlers.CreateUserHandler
//import example.com.application.commandhandlers.DeleteUserHandler
//import example.com.application.commandhandlers.UpdateUserHandler
//import example.com.application.commands.DeleteUserCommand
//import example.com.application.queries.FindUserByIdQuery
//import example.com.application.queryhandlers.FindUserByIdHandler
import utn.methodology.infrastructure.http.actions.createUserAction
//import example.com.infrastructure.http.actions.DeleteUserAction
//import example.com.infrastructure.http.actions.FindUserByIdAction
//import example.com.infrastructure.http.actions.UpdateUserAction
import utn.methodology.infrastructure.persistence.MongoUserRepository
import utn.methodology.infrastructure.persistence.connectToMongoDB
import io.ktor.http.*
import io.ktor.server.application.*
/*import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.* */

fun Application.userRouter() {
    val mongoDatabase = connectToMongoDB()

    val userMongoUserRepository = MongoUserRepository(mongoDatabase)

    val createUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository)) 
    /* 
    val updateUserAction = UpdateUserAction(UpdateUserHandler(userMongoUserRepository))
    val findUserByIdAction = FindUserByIdAction(FindUserByIdHandler(userMongoUserRepository))
    val deleteUserAction = DeleteUserAction(DeleteUserHandler(userMongoUserRepository))
    */
    routing {

        post("/users") {
            val body = call.receive<CreateUserCommand>()
            createUserAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }
        /*
        put("/users/{id}") {
            val body = call.receive<UpdateUserCommand>()

            body.id = call.parameters["id"].toString() ?: throw IllegalArgumentException("No ID Found")

            updateUserAction.execute(body);

            call.respond(HttpStatusCode.OK, mapOf("message" to "updated"))
        }

        get("/users/{id}") {

            val query = FindUserByIdQuery(call.parameters["id"].toString())

            val result = findUserByIdAction.execute(query)

            call.respond(HttpStatusCode.OK, result)

        }

        get("/users") {
            val users = userMongoUserRepository.findAll();

            call.respond(HttpStatusCode.OK, users.map { it.toPrimitives() })
        }


        delete("/users/{id}") {

            val query = DeleteUserCommand(call.parameters["id"].toString())

            deleteUserAction.execute(query)

            call.respond(HttpStatusCode.NoContent)
        }
        */
    }
}