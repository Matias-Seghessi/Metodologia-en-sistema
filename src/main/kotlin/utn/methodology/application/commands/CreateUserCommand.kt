package utn.methodology.application.commands

import kotlinx.serialization.Serializable
import utn.methodology.domain.entities.User

@Serializable()
        data class CreateUserCommand(
        val nombre: String,
        val username: String,
        val email: String,
        val contraseñia: String,
            ) {
                fun validate(): CreateUserCommand {
                    checkNotNull(nombre) { throw IllegalArgumentException("Name must be defined") }
                    checkNotNull(username) { throw IllegalArgumentException("LastName must be defined") }
                    checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }
                    checkNotNull(contraseñia) { throw IllegalArgumentException("Email must be defined") }

                    return this;
                }
            }
