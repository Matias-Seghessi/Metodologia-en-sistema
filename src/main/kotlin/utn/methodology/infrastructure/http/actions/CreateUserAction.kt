package utn.methodology.infrastructure.http.actions

import utn.methodology.application.commands.CreateUserCommand
import utn.methodology.application.commandhandlers.CreateUserHandler
import utn.methodology.infrastructure.http.dtos.CreateUserRequestBody

class CreateUserAction(
    private val handler: CreateUserHandler
) {

    fun execute(body: CreateUserRequestBody) {

        body.validate().let {
            val command = CreateUserCommand(
                it.nombre,
                it.username,
                it.email,
                it.contrasenia
            )

            handler.handle(command)
        }

    }
}