package utn.methodology.domain.entities.User

import java.util.UUID

data class User(

    var uuid: UUID = UUID.randomUUID(),
    var nombre: String,
    var username: String,
    var email: String,
    var contraseñia: String
){
    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "uuid" to uuid.toString(),  // Convertir UUID a String
            "nombre" to nombre,
            "username" to username,
            "email" to email,
            "contrasenia" to contraseñia
        )
    }
}

data class SearchUserQuery(
    var uuid: String,
    var nombre: String,
    var username: String,
    var email: String,
    var contraseñia: String
)
