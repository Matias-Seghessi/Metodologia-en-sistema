package utn.methodology.infrastructure.http.router

import utn.methodology.application.commandhandlers.CreateUserHandler
import utn.methodology.infrastructure.http.actions.CreateUserAction
import utn.methodology.infrastructure.persistence.MongoUserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utn.methodology.infrastructure.http.dtos.CreateUserRequestBody
import utn.methodology.infrastructure.persistence.connectToMongoDB


fun Application.userRouter() {
    val mongoDatabase = connectToMongoDB()
    
    val userMongoUserRepository = MongoUserRepository(mongoDatabase)
    
    val createUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository))
    
    routing {

        post("/users") {
            val body = call.receive<CreateUserRequestBody>()
            createUserAction.execute(body)

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }

        get("/users") {
            val users = userMongoUserRepository.findAll()

            call.respond(HttpStatusCode.OK, users.map { it.toPrimitives() })
        }
    }
}