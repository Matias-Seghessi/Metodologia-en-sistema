package utn.methodology.application.queries

data class FindPostsByPostsnameQuery(
    val postname: String
) {

    fun validate(): FindPostsByPostsnameQuery {
        check(postname.isNotEmpty()) { throw IllegalArgumentException("Postname must be defined") }
        return this
    }
}