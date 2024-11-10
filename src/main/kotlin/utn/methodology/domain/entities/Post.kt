package utn.methodology.domain.entities.Post

import java.time.LocalDateTime

data class Post(
    val userId: String,
    val titulo: String,
    val contenido: String,
    val autor: String,
    val fechaCreacion: LocalDateTime = System.currentTimeMillis()
) {
    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "userId" to userId,
            "titulo" to titulo,
            "contenido" to contenido,
            "autor" to autor,
            "fechaCreacion" to fechaCreacion
        )
    }
}