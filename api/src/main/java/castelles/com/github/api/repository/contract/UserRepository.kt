package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.User
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(
        user: User,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<Unit>>
}