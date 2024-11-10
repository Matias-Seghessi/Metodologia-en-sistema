package utn.methodology.shared.mocks

import utn.methodology.domain.contracts.UserRepository
import utn.methodology.domain.entities.User

class MockUserRepository : UserRepository {

    private var users: MutableList<User> = mutableListOf()

    override fun save(user: User) {
        users.removeIf { it.uuid == user.uuid }
        users.add(user)
    }

    override fun findOne(id: String): User? {
        return users.find { it.uuid.toString() == id }
    }

    override fun delete(user: User) {
        users.removeIf { it.uuid == user.uuid }
    }

    fun clean() {
        users = mutableListOf()
    }

    fun findByUsername(username: String): User? {
        return users.find { it.username == username }
    }
}
