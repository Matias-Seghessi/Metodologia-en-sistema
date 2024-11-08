package utn.methodology.application.queryhandlers

import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.Application.queries.FindPostsByPostsnameQuery

class FindPostsByPostsnameHandler(
    private val postRepository: MongoPostRepository
) {

    fun handle(query: FindPostsByPostsnameHandler): Map<String, String> {
        val post = postRepository.findOne(query.Postname)
        if (post == null) {
            throw NotFoundException("Post with Postname: ${query.Postname} not found")
        }
        return post.toPrimitives()
    }
}