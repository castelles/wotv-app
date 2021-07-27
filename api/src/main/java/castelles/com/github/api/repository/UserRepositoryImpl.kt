package castelles.com.github.api.repository

import castelles.com.github.api.datasource.UserDataSource
import castelles.com.github.api.model.User
import castelles.com.github.api.repository.contract.UserRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val dataSource: UserDataSource
): UserRepository {

    override suspend fun createUser(
        user: User,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<Unit>> =
        flow {
            emit(Loading)
            val result = dataSource.createUser(user)
            emit(result)
        }.flowOn(dispatcher)

}