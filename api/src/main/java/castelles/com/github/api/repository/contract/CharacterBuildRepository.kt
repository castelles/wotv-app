package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.CharacterBuildDto
import castelles.com.github.api.model.CharacterBuildResponse
import castelles.com.github.api.model.Waterfall
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface CharacterBuildRepository {

    suspend fun buildTeam(
        team: CharacterBuildDto,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<Unit>>

    suspend fun getBuilds(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<CharacterBuildResponse>>>
}