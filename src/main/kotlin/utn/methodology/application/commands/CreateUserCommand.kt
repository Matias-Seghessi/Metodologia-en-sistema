import kotlinx.serialization.Serializable
import kotlin.math.E


@Serializable()
data class CreateUserCommand(
    val nombre: String,
    val userName: String,
    val email: String,
    val contraseñia: String,
) {
    fun validate(): CreateUserCommand {
        checkNotNull(nombre) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(userName) { throw IllegalArgumentException("LastName must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }
        checkNotNull(contraseñia) {throw IllegalArgumentException("Email must be defined") }
        return this;
    }
}