package utn.methodology.infrastructure.http.actions

import utn.methodology.domain.contracts.PostRepository
import utn.methodology.domain.entities.Post.Post
import javax.sql.DataSource

class PostRepositoryImpl(private val dataSource: DataSource) : PostRepository {
  override fun findPostsByFollowedUsers(userId: Long): List<Post> {
      val sql = """
          SELECT p.user_id, p.title, p.content, p.author, p.publication_date
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
                  userId = resultSet.getLong("user_id").toString(),
                  titulo = resultSet.getString("title"),
                  contenido = resultSet.getString("content"),
                  autor = resultSet.getString("author"),
                  fechaCreacion = resultSet.getTimestamp("publication_date").toLocalDateTime()
              )
          )
      }
      resultSet.close()
      preparedStatement.close()
      connection.close()
      return posts
  }
}
