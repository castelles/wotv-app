package castelles.com.github.api.repository

import castelles.com.github.api.datasource.EsperDataSource
import castelles.com.github.api.model.Esper
import castelles.com.github.api.repository.contract.EsperRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EsperRepositoryImpl(
    private val dataSource: EsperDataSource
): EsperRepository {

    override suspend fun getEsper(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<Esper>> = flow {
        emit(Loading)
        val result = dataSource.getEsper(id)
        emit(result)
    }

    override suspend fun getEspers(
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<Esper>>> = flow {
        emit(Loading)
        val result = dataSource.getEspers()
        emit(result)
    }.flowOn(dispatcher)

}