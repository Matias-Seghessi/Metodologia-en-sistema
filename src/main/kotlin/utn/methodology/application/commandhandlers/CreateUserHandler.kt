package utn.methodology.application.commandhandlers

import utn.methodology.domain.entities.User.User
import utn.methodology.application.commands.CreateUserCommand

import java.util.UUID


class CreateUserHandler(
        private val userRepository: utn.methodology.domain.contracts.UserRepository
){
    fun handle(command: CreateUserCommand){
        val user = User(
            UUID.randomUUID(),
            command.nombre,
            command.userName,
            command.email,
            command.contraseñia
        )
        userRepository.save(user)
    }


}