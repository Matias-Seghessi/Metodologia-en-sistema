package utn.methodology.infrastructure.http.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequestBody(
    var nombre: String = "",
    var username: String = "",
    var email: String = "",
    var contrasenia: String = ""
) {

    fun validate(): CreateUserRequestBody {
        check(nombre.isNotEmpty()) { throw IllegalArgumentException("Name must be defined and not empty") }
        check(username.isNotEmpty()) { throw IllegalArgumentException("Username must be defined and not empty") }
        check(email.isNotEmpty()) { throw IllegalArgumentException("Email must be defined and not empty") }
        check(contrasenia.isNotEmpty()) { throw IllegalArgumentException("Password must be defined and not empty") }

        return this
    }
}