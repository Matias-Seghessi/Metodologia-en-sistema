package utn.methodology.domain.entities.User

import utn.Application.commands.CreateUserCommand
import utn.Application.commandhandlers.CreateUserHandler

class CreateUserAction(
    private val handler: CreateUserHandler
) {

    fun execute(body: CreateUserCommand) {

        body.validate().let {
            handler.handle(it)
        }

    }
}