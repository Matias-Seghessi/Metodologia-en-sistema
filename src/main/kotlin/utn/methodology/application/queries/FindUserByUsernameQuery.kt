package utn.methodology.application.queries

data class FindUserByUsernameQuery(
    val username: String
) {

    fun validate(): FindUserByUsernameQuery {
        check(username.isNotEmpty()) { throw IllegalArgumentException("Username must be defined") }
        return this
    }
}