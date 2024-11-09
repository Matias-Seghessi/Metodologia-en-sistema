package utn.methodology.application.commandhandlers

import utn.methodology.domain.entities.Post.Post
import utn.methodology.infrastructure.persistence.MongoPostRepository
import utn.methodology.application.commands.CreatePostCommand

import java.util.UUID


class CreatePostHandler(
        private val postRepository: MongoPostRepository
){
    fun handle(command: CreatePostCommand) {
        val post = Post(
            userId = UUID.randomUUID().toString(),
            titulo = command.titulo,
            contenido = command.contenido,
            autor = command.autor,
            fechaCreacion = System.currentTimeMillis()
        )
        postRepository.save(post)
    }
}
