import utn.methodology.application.view.ViewFollowedPosts
import utn.methodology.domain.controllers.PostController
import utn.methodology.infrastructure.http.actions.PostRepositoryImpl

fun main() {
  val dataSource = ... // Configuración del DataSource
  val postRepository = PostRepositoryImpl(dataSource)
  val viewFollowedPosts = ViewFollowedPosts(postRepository)
  val postController = PostController(viewFollowedPosts)
  
  // Configuración del servidor (por ejemplo, Ktor, Spring Boot)
}
