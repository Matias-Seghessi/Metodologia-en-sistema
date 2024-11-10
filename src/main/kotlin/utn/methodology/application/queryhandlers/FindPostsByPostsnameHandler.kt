package utn.methodology.application.queryhandlers

import io.ktor.server.plugins.*
import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.application.queries.FindPostsByPostsnameQuery

class FindPostsByPostsnameHandler(
    private val postRepository: MongoPostRepository
) {

    fun handle(query: FindPostsByPostsnameQuery): Map<String, Any> {

        val post = postRepository.findOne(query.postname)
            ?: throw NotFoundException("user with id: ${query.postname} not found")

        return post.toPrimitives()
    }
}