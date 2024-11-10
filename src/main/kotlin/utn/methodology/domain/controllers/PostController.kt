package utn.methodology.domain.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.response.*
import io.ktor.routing.*
import utn.methodology.application.view.ViewFollowedPosts

fun Application.postRoutes(viewFollowedPosts: ViewFollowedPosts) {
    routing {
        route("/posts") {
            get("/user/{userId}") {
                val userId = call.parameters["userId"]?.toLongOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid userId")
                    return@get
                }
                val posts = viewFollowedPosts.execute(userId)
                call.respond(HttpStatusCode.OK, posts)
            }
        }
    }
}

