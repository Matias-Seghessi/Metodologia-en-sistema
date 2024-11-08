fun main() {
  val dataSource = ... // Configuración del DataSource
  val postRepository = PostRepositoryImpl(dataSource)
  val viewFollowedPosts = ViewFollowedPosts(postRepository)
  val postController = PostController(viewFollowedPosts)
  
  // Configuración del servidor (por ejemplo, Ktor, Spring Boot)
}
