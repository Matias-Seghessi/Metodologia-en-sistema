package utn.methodology.application.queryhandlers

import io.ktor.server.plugins.*
import utn.methodology.infrastructure.persistence.MongoUserRepository
import utn.methodology.application.queries.FindUserByUsernameQuery

class FindUserByUsernameHandler(
    private val userRepository: MongoUserRepository
) {

    fun handle(query: FindUserByUsernameQuery): Map<String, Any> {

        val user = userRepository.findOne(query.username)
            ?: throw NotFoundException("user with username: ${query.username} not found")

        return user.toPrimitives()
    }
}