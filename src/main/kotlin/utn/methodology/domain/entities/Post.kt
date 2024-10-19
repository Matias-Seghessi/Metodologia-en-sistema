package utn.methodology.domain.entities.Post
data class Post(
    val userId: String,
    val message: String,
    val createdAt: Long = System.currentTimeMillis()
)