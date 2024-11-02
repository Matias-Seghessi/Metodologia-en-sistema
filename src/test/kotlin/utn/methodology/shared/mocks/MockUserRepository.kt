package utn.methodology.shared.mocks

import utn.methodology.domain.contracts.UserRepository
import utn.methodology.domain.entities.User

class MockUserRepository : UserRepository {

    var users: Array<User> = emptyArray()

    override fun save(user: User) {
        users = users.filter { it.getId() != user.getId() }.toTypedArray()

        users = users.plus(user)
    }

    override fun findOne(id: String): User? {
        return users.find { it.getId() == id }
    }

    override fun delete(user: User) {
        users = users.filter { it.getId() != user.getId() }.toTypedArray()
    }

    fun clean() {
        users = emptyArray();
    }

    fun findByUsername(username: String): User? {
        return users.find { it.getUsername() == username }
    }
}