package utn.methodology.unit.application.commandhandlers

import utn.methodology.application.commandhandlers.CreateUserHandler
import utn.methodology.application.commands.CreateUserCommand
import utn.methodology.shared.mocks.MockUserRepository
import utn.methodology.shared.mothers.UserMother
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertFailsWith

class CreateUserHandlerTest {

    private val mockUserRepository = MockUserRepository()
    private var sut: CreateUserHandler = CreateUserHandler(mockUserRepository)

    @BeforeTest
    fun beforeEach() {
        mockUserRepository.clean()
    }

    @Test
    fun create_user_should_return_201() {
        // Arrange
        val nombre = UserMother.faker.southPark.characters()
        val username = UserMother.faker.southPark.characters()
        val email = UserMother.faker.internet.email()
        val password = UserMother.faker.password()

        val command = CreateUserCommand(
            nombre,
            username,
            email,
            password
        )

        // Act
        sut.handle(command)

        // Assert
        val user = mockUserRepository.findByUsername(username)
        assertNotNull(user)
        assert(user.email == command.email)
    }

    @Test
    fun create_user_should_return_400() {
        // Arrange
        val invalidName = ""
        val invalidUsername = "" // Nombre de usuario inválido (vacío)
        val invalidEmail = ""
        val invalidPassword = ""

        val command = CreateUserCommand(
            invalidName,
            invalidUsername,
            invalidEmail,
            invalidPassword
        )

        // Act & Assert
        val exception = assertFailsWith<IllegalArgumentException> {
            sut.handle(command)
        }

        // Verificar el mensaje de error
        assert(exception.message == "Username cannot be empty")
    }
}
