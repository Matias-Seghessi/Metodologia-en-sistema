package utn.methodology.domain.entities

import java.util.UUID

data class User(

    var uuid: UUID = UUID.randomUUID(),
    var nombre: String,
    var username: String,
    var email: String,
    var contrasenia: String
) {


    // Relaciones de seguimiento
    var followers: MutableSet<User> = mutableSetOf()  // Usuarios que siguen a este usuario
    var follows: MutableSet<User> = mutableSetOf()     // Usuarios que este usuario sigue

    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "uuid" to uuid.toString(),  // Convertir UUID a String
            "nombre" to nombre,
            "username" to username,
            "email" to email,
            "contrasenia" to contrasenia,
            "followers" to followers,
            "follows" to follows
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, Any>): User {

            val user = User(
                primitives["uuid"] as UUID,
                primitives["nombre"] as String,
                primitives["username"] as String,
                primitives["email"] as String,
                primitives["contrasenia"] as String
            )

            user.followers = primitives["followers"] as MutableSet<User>
            user.follows = primitives["follows"] as MutableSet<User>

            return user
        }
    }
}
