package utn.methodology.application.commandhandlers


import io.ktor.server.plugins.*
import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.application.commands.DeletePostCommand

class DeletePostsHandler(
    private val postRepository: MongoPostRepository
) {

    fun handle(command: DeletePostCommand) {

        val post = postRepository.findOne(command.postId)
            ?: throw NotFoundException ("not found user with id: ${command.postId}")

        postRepository.delete(post)
    }
}

