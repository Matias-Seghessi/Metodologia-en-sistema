package utn.methodology.application.commands

class DeletePostCommand(
    val postId: String
) {

    fun validate(): DeletePostCommand {
        return this
    }
}