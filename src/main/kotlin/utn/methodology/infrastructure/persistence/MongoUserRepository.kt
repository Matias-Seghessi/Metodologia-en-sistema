package utn.methodology.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import utn.methodology.domain.entities.User.User

class MongoUserRepository(private val database: MongoDatabase) {

    // Cambiado el tipo de la colección a MongoCollection<Document>
    private var collection: MongoCollection<Document>

    init {
        collection = database.getCollection("users")
    }

    fun save(user: User) {
        val options = UpdateOptions().upsert(true)

        val filter = Document("_id", user.uuid) // Usa el campo uuid como filtro
        val update = Document("\$set", user.toPrimitives()) // Convertir User a primitivos


        collection.updateOne(filter, update, options) // Actualiza o inserta
    }


}




