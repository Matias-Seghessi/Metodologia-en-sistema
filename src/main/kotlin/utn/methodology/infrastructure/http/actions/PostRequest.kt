package utn.methodology.infrastructure.http.actions

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val userId: Int,
    val message: String
)