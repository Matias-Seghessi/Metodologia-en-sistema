package utn.methodology.infrastructure.http.actions;

import utn.Application.queries.FindUserByUsernameQuery;
import utn.Application.queryhandlers.FindUserByUsernameHandler;

class FindUserByUsernameAction(
    private val handler: FindUserByUsernameHandler
) {

    fun execute(query: FindUserByUsernameQuery) {
        query
            .validate()
            .let {
                return handler.handle(it)
            }

    }
}