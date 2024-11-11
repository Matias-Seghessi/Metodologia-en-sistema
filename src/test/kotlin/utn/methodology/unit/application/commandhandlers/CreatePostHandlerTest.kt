package utn.methodology.unit.application.commandhandlers

import utn.methodology.application.commandhandlers.CreatePostHandler
import utn.methodology.application.commands.CreatePostCommand
import utn.methodology.shared.mocks.MockPostRepository
import utn.methodology.shared.mocks.MockUserRepository
import utn.methodology.shared.mothers.PostMother
import utn.methodology.shared.mothers.UserMother
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreatePostHandlerTest {

    private val mockUserRepository: MockUserRepository = MockUserRepository()
    private val mockPostRepository: MockPostRepository = MockPostRepository()

    private var sut: CreatePostHandler = CreatePostHandler(mockUserRepository, mockPostRepository)

    @BeforeTest
    fun beforeEach() {
        mockPostRepository.clean()
        mockUserRepository.clean()
    }

    @Test
    fun create_user_should_return_201() {
        // arrange
        val user = UserMother.random()
        val titulo = PostMother.faker.southPark.quotes()
        val contenido = PostMother.faker.southPark.quotes()
        val autor = PostMother.faker.southPark.quotes()
        val fechaCreacion = PostMother.LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

        mockUserRepository.save(user)

        val command = CreatePostCommand(
            user.getId().toString(),
            titulo,
            contenido,
            autor,
            fechaCreacion
        )

        // act
        sut.handle(command)

        // assertions
        val posts = mockPostRepository.findByOwnerId(user.getId())

        assert(posts.size == 1)
        assert(posts[0].getContent() === content)
    }

    @Test
    fun create_user_should_return_400() {
        // Arrange
        val invalidUser = ""
        val invalidTitle = "" // Nombre de usuario inválido (vacío)
        val invalidContent = ""
        val invalidAuthor = ""
        val invalidFechaCreacion = ""

        val command = CreateUserCommand(
            invalidUser,
            invalidTitle,
            invalidContent,
            invalidAuthor,
            invalidFechaCreacion
        )

        // Act & Assert
        val exception = assertFailsWith<IllegalArgumentException> {
            sut.handle(command)
        }

        // Verificar el mensaje de error
        assert(exception.message == "User cannot be empty")
    }
}