import kotlinx.serialization.Serializable

@Serializable()
        data class CreateUserCommand(
        val email: String,
            ) {
                fun validate(): CreateUserCommand {
                    checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }

                    return this;
                }
            }