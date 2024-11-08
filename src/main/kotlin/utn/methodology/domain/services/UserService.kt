package utn.methodology.domain.services

import utn.methodology.domain.entities.User

class UserService {

    // Función para que un usuario siga a otro
    fun followUser(follower: User, followed: User): String {
        if (!follower.seguidos.contains(followed)) {
            follower.seguidos.add(followed)
            followed.seguidores.add(follower)
            return "${follower.username} está siguiendo a ${followed.username}"
        }
        return "${follower.username} ya sigue a ${followed.username}"
    }

    // Función para que un usuario deje de seguir a otro
    fun unfollowUser(follower: User, followed: User): String {
        if (follower.seguidos.contains(followed)) {
            follower.seguidos.remove(followed)
            followed.seguidores.remove(follower)
            return "${follower.username} dejó de seguir a ${followed.username}"
        }
        return "${follower.username} no sigue a ${followed.username}"
    }
}