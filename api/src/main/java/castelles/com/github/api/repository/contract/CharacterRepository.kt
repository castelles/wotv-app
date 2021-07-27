package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.Character
import castelles.com.github.api.model.JobResponse
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacter(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<Character>>

    suspend fun getCharacterJobs(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<JobResponse>>>

    suspend fun getCharacters(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<Character>>>
}