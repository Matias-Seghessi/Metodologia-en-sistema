package utn.methodology.domain.entities.Post

import utn.Application.commands.CreatePostCommand
import utn.Application.commandhandlers.CreatePostHandler

class CreatePostAction(
    private val handler: CreatePostHandler
) {
    fun execute(body: CreatePostCommand) {
        body.validate().let {
            handler.handle(it)
        }
    }
}