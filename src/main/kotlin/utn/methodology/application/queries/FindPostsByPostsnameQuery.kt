package utn.methodology.application.queries

data class FindPostsByPostsnameQuery(
    val Postname: String
) {

    fun validate(): FindPostsByPostsnameQuery {
        check(Postname.isNotEmpty()) { throw IllegalArgumentException("Postname must be defined") }
        return this
    }
}