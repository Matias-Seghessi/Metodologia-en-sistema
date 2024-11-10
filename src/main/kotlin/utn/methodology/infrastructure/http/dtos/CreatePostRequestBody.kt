package utn.methodology.infrastructure.http.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequestBody(
    val userId: String = "",
    val contenido: String = "",
    val titulo: String = "",
    val autor: String = "",
) {

    fun validate(): CreatePostRequestBody {
        check(titulo.isNotEmpty()) { throw IllegalArgumentException("Title must be defined and not empty") }
        check(contenido.isNotEmpty()) { throw IllegalArgumentException("Content must be defined and not empty") }
        check(autor.isNotEmpty()) { throw IllegalArgumentException("Author must be defined and not empty") }

        return this
    }
}