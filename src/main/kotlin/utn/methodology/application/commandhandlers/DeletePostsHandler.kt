package utn.methodology.application.commandhandlers

import utn.methodology.domain.entities.Post.Post
import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.application.commands.DeletePostCommand

import java.util.UUID

class DeletePostCommand(
    private val PostRepository: MongoPostRepository
) {

    fun handle(command: DeletePostCommand) {

        val post = PostRepository.findOne(command.id)

        if (post == null) {
            throw NotFoundException("not found user with id: ${command.id}")
        }

        PostRepository.delete(post);
    }
}

