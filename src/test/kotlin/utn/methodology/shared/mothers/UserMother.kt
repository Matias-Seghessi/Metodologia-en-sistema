package utn.methodology.shared.mothers

import utn.methodology.domain.entities.User
import io.github.serpro69.kfaker.Faker
import java.util.UUID

class UserMother {

    companion object {
        val faker = Faker()

        fun random(): User {
            return User.fromPrimitives(
                mapOf(
                    "uuid" to UUID.randomUUID().toString(),
                    "nombre" to faker.southPark.characters(),
                    "username" to faker.southPark.characters(),
                    "email" to faker.internet.email(),
                    "contraseñia" to faker.password(),
                    "followers" to emptyList<String>(),
                    "follows" to emptyList<String>()
                )
            )
        }

    }
}