package utn.methodology.domain.entities.Post
data class Post(
    val userId: String,
    val titulo: String,
    val contenido: String,
    val autor: String,
    val fechaCreacion: Long = System.currentTimeMillis()
)