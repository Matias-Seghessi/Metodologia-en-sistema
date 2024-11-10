package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commands.CreatePostCommand
import utn.methodology.application.commandhandlers.CreatePostHandler
import utn.methodology.infrastructure.http.dtos.CreatePostRequestBody

class CreatePostAction(
    private val handler: CreatePostHandler
) {

    fun execute(body: CreatePostRequestBody) {

        body.validate().let {
            val command = CreatePostCommand(
                it.userId,
                it.titulo,
                it.contenido,
                it.autor
            )

            handler.handle(command)
        }
    }
}