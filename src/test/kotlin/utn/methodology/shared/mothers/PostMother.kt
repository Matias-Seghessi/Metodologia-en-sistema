package utn.methodology.shared.mothers

import utn.methodology.domain.entities.Post
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

class PostMother {

    companion object {
        val faker = Faker()

        fun random(userId: String): Post {
            return Post.fromPrimitives(
                mapOf(
                    "userId" to userId,
                    "titulo" to faker.southPark.characters(),
                    "contenido" to faker.lorem.words(),
                    "autor" to faker.southPark.characters(),
                    "fechaCreacion" to LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                )
            )
        }
    }
}