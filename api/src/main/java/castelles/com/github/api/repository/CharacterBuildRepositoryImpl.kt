package castelles.com.github.api.repository

import castelles.com.github.api.datasource.CharacterBuildDataSource
import castelles.com.github.api.model.CharacterBuildDto
import castelles.com.github.api.model.CharacterBuildResponse
import castelles.com.github.api.repository.contract.CharacterBuildRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterBuildRepositoryImpl(
    private val dataSource: CharacterBuildDataSource
): CharacterBuildRepository {

    override suspend fun buildTeam(
        team: CharacterBuildDto,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<Unit>> = flow {
        emit(Loading)
        val result = dataSource.buildTeam(team)
        emit(result)
    }.flowOn(dispatcher)

    override suspend fun getBuilds(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<CharacterBuildResponse>>> = flow {
        emit(Loading)
        val result = dataSource.getBuilds(id)
        emit(result)
    }.flowOn(dispatcher)


}