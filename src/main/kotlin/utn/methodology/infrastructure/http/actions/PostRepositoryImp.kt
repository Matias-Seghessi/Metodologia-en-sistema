class PostRepositoryImpl(private val dataSource: DataSource) : PostRepository {
  override fun findPostsByFollowedUsers(userId: Long): List<Post> {
      val sql = """
          SELECT p.post_id, p.user_id, p.content, p.publication_date
          FROM posts p
          JOIN follows f ON p.user_id = f.followed_id
          WHERE f.follower_id = ?
          ORDER BY p.publication_date DESC
      """
      val posts = mutableListOf<Post>()
      val connection = dataSource.connection
      val preparedStatement = connection.prepareStatement(sql)
      preparedStatement.setLong(1, userId)
      val resultSet = preparedStatement.executeQuery()
      while (resultSet.next()) {
          posts.add(
              Post(
                  postId = resultSet.getLong("post_id"),
                  userId = resultSet.getLong("user_id"),
                  content = resultSet.getString("content"),
                  publicationDate = resultSet.getTimestamp("publication_date").toLocalDateTime()
              )
          )
      }
      resultSet.close()
      preparedStatement.close()
      connection.close()
      return posts
  }
}
