package utn.methodology.application.view

import utn.methodology.domain.contracts.postRepository
import utn.methodology.domain.entities.Post.Post

class ViewFollowedPosts(private val postRepository: PostRepository) {
  fun execute(userId: Long): List<Post> {
      return postRepository.findPostsByFollowedUsers
  }
}
