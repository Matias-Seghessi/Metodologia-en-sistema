package utn.methodology.domain.contracts

import utn.methodology.domain.entities.Post.Post

interface PostRepository {
  fun findPostsByFollowedUsers(userId: Long): List<Post>
}