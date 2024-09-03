package utn.methodology.domain.entities

import java.util.UUID

data class User(

    var uuid: UUID = UUID.randomUUID(),

    var nombre: String,

    var username: String,

    var email: String,

    var contraseña: String
)