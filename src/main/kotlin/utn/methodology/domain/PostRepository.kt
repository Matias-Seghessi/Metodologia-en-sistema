interface PostRepository {
  fun findPostsByFollowedUsers(userId: Long): List<Post>
}
