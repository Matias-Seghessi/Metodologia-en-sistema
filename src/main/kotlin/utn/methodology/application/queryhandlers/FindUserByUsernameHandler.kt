package utn.methodology.application.queryhandlers

import utn.methodology.infrastructure.persistence.MongoUserRepository
import utn.Application.queries.FindUserByUsernameQuery;
class FindUserByUsernameHandler(
    private val userRepository: MongoUserRepository
) {

    fun handle(query: FindUserByUsernameQuery): Map<String, String> {

        val user = userRepository.findOne(query.username)

        if (user == null) {
            throw NotFoundException("user with username: ${query.username} not found")
        }

        return user.toPrimitives()
    }
}