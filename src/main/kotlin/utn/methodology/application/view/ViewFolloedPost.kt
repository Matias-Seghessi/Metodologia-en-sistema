package utn.methodology.application.view

import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.domain.entities.Post.Post

class ViewFollowedPosts(private val postRepository: MongoPostRepository) {
  fun execute(userId: Long): List<Post> {
      return postRepository.findPostsByFollowedUsers
  }
}
