package utn.methodology.application.commands

import kotlinx.serialization.Serializable
import kotlin.math.E

data class CreatePostCommand(
    val titulo: String,
    val contenido: String,
    val autor: String
)