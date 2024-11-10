package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commands.DeletePostCommand
import utn.methodology.application.commandhandlers.DeletePostsHandler

class DeletePostAction(
    private val handler: DeletePostsHandler
) {

    fun execute(command: DeletePostCommand) {
        command.validate().let {
            handler.handle(it)
        }
    }
}