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

    val mockUserRepository = MockUserRepository()

    var sut: CreateUserHandler = CreateUserHandler(mockUserRepository)

    @BeforeTest
    fun beforeEach() {
        mockUserRepository.clean()
    }

    @Test
    fun create_user_should_returns_201() {
        // arrange
        val nombre = UserMother.faker.southPark.characters();
        val username = UserMother.faker.southPark.characters();

        val command = CreateUserCommand(
            nombre,
            username,
            UserMother.faker.internet.email(),
            UserMother.faker.password()
        )

        // act
        sut.handle(command)

        //assertion
        val user = mockUserRepository.findByUsername(username)

        assertNotNull(user)
        assert(user.getEmail() == command.email)
    }

    @Test
    fun create_user_should_returns_400() {
        // Arrange
        val invalidUsername = "" // Nombre de usuario inválido (vacío)

        val command = CreateUserCommand(
            invalidUsername,
            UserMother.faker.internet.email(),
        )

        // Act & Assert
        val exception = assertFailsWith<IllegalArgumentException> {
            sut.handle(command)
        }

        // Verificar el mensaje de error
        assert(exception.message == "Username cannot be empty")
    }
}