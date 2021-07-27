package castelles.com.github.api.repository

import castelles.com.github.api.datasource.CharacterDataSource
import castelles.com.github.api.model.Character
import castelles.com.github.api.model.JobResponse
import castelles.com.github.api.repository.contract.CharacterRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import castelles.com.github.api.utils.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepositoryImpl(
    private val dataSource: CharacterDataSource
): CharacterRepository {

    override suspend fun getCharacter(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<Character>> = flow {
        emit(Loading)
        val result = dataSource.getCharacter(id)
        emit(result)
    }.flowOn(dispatcher)

    override suspend fun getCharacterJobs(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<JobResponse>>> = flow {
        emit(Loading)
        val result = dataSource.getCharacterJobs(id)
        emit(result)
    }.flowOn(dispatcher)

    override suspend fun getCharacters(
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<Character>>> = flow {
        emit(Loading)
        val result = dataSource.getCharacters()
        emit(result)
    }.flowOn(dispatcher)
}