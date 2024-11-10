package utn.methodology.domain.controllers

import org.springframework.web.bind.annotation.*
import utn.methodology.domain.entities.User
import utn.methodology.domain.services.UserService

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    // Ruta para que un usuario siga a otro
    @PostMapping("/{followerUuid}/follow/{followedUuid}")
    fun followUser(@PathVariable followerUuid: UUID, @PathVariable followedUuid: UUID): String {
        val follower = getUserByUuid(followerUuid)  // Asumimos que existe una función para obtener el usuario por UUID
        val followed = getUserByUuid(followedUuid)  // Lo mismo para el usuario seguido
        return userService.followUser(follower, followed)
    }

    // Ruta para que un usuario deje de seguir a otro
    @PostMapping("/{followerUuid}/unfollow/{followedUuid}")
    fun unfollowUser(@PathVariable followerUuid: UUID, @PathVariable followedUuid: UUID): String {
        val follower = getUserByUuid(followerUuid)
        val followed = getUserByUuid(followedUuid)
        return userService.unfollowUser(follower, followed)
    }

    // Simulación de la obtención de un usuario por UUID
    private fun getUserByUuid(uuid: UUID): User {
        // Aquí deberías conectar a la base de datos para obtener el usuario, pero por ahora lo simulamos:
        return User(uuid = uuid, nombre = "Usuario $uuid", username = "usuario$uuid", email = "$uuid@correo.com", contrasenia = "123456")
    }
}