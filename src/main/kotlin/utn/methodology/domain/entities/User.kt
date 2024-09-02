package example.com.app.domain.entity

import example.com.app.domain.events.DomainEvent
import example.com.app.domain.events.ShippingCreated
import example.com.app.domain.events.ShippingHasNeedPrepared
import example.com.app.domain.events.ShippingReadyToDeliver
import example.com.app.domain.valueObjects.*
import java.util.UUID

data class User(

    var uuid: UUID = UUID.randomUUID(),

    var nombre: String,

    var username: String,

    var email: String,

    var contraseña: String
)