package utn.methodology.application.commandhandlers

import utn.methodology.domain.entities.User
import utn.methodology.application.commands.CreateUserCommand
import utn.methodology.infrastructure.persistence.MongoUserRepository

import java.util.UUID


class CreateUserHandler(
        private val userRepository: MongoUserRepository
){
    fun handle(command: CreateUserCommand){
        val user = User(
            UUID.randomUUID(),
            command.nombre,
            command.userName,
            command.email,
            command.contrasenia
        )
        userRepository.save(user)
    }
}