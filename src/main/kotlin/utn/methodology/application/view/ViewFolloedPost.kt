class ViewFollowedPosts(private val postRepository: PostRepository) {
  fun execute(userId: Long): List<Post> {
      return postRepository.findPostsByFollowedUsers(userId)
  }
}
