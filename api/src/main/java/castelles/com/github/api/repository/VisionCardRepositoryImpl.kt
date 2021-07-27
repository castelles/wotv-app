package castelles.com.github.api.repository

import castelles.com.github.api.datasource.VisionDataSource
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.repository.contract.VisionCardRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VisionCardRepositoryImpl(
    private val dataSource: VisionDataSource
): VisionCardRepository {

    override suspend fun getVisionCard(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<VisionCard>> = flow {
        emit(Loading)
        val result = dataSource.getVisionCard(id)
        emit(result)
    }.flowOn(dispatcher)

    override suspend fun getVisionCards(
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<VisionCard>>> = flow {
        emit(Loading)
        val result = dataSource.getVisionCards()
        emit(result)
    }.flowOn(dispatcher)

}