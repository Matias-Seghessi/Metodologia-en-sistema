package utn.methodology.domain.entities.Post

import utn.Application.commands.DeletePostCommand
import utn.Application.commandhandlers.DeletePostsHandler

class DeletePostAction(
    private val handler: DeletePostsHandler
) {

    fun execute(command: DeletePostCommand) {
        command.validate().let {
            handler.handle(it)
        }
    }
}