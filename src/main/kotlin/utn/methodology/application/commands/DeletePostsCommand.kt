package utn.methodology.application.commands

class DeletePostCommand(
    val userId: String
) {

    fun validate(): DeleteUserCommand{
        checkNotNull(userId) {throw IllegalArgumentException("Id must be defined")}
        return this
    }
}