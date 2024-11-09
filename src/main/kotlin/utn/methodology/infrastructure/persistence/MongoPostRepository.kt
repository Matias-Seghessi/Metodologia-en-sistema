package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import utn.methodology.domain.entities.Post.Post
import utn.methodology.domain.entities.Post
import org.litote.kmongo.*

class MongoPostRepository(private val database: MongoDatabase) {

    // Cambiado el tipo de la colección a MongoCollection<Document>
    private val collection: MongoCollection<Document> = database.getCollection("posts")

    fun save(post: Post) {
        val document = Document().apply {
            put("id", post.id.toString())
            put("titulo", post.titulo)
            put("contenido", post.contenido)
            put("autor", post.autor)
            put("fechaCreacion", post.fechaCreacion)
        }
        collection.insertOne(document)
    }
}
class MongoPostRepository(private val database: MongoDatabase) {

    fun getPostsByUser(userId: Int, order: String, limit: Int, offset: Int): List<Post> {
        val collection = database.getMongoDatabase().getCollection<Post>("posts")

        val query = eq("userId", userId)

        val sortOrder = if (order == "ASC") {
            ascending(Post::createdAt)
        } else {
            descending(Post::createdAt)
        }

        return collection.find(query)
            .sort(sortOrder)
            .skip(offset)
            .limit(limit)
            .toList()
    }
}