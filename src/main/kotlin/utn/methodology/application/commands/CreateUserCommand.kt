package utn.methodology.application.commands

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserCommand(
    val nombre: String,
    val userName: String,
    val email: String,
    val contrasenia: String,
) {
    fun validate(): CreateUserCommand {
        return this
    }
}