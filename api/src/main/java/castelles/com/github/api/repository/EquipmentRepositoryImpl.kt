package castelles.com.github.api.repository

import castelles.com.github.api.datasource.EquipmentDataSource
import castelles.com.github.api.model.Equipments
import castelles.com.github.api.repository.contract.EquipmentRepository
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EquipmentRepositoryImpl(
    private val dataSource: EquipmentDataSource
): EquipmentRepository {

    override suspend fun getEquipment(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<Equipments>> = flow {
        emit(Loading)
        val result = dataSource.getEquipment(id)
        emit(result)
    }.flowOn(dispatcher)

    override suspend fun getEquipments(
        dispatcher: CoroutineDispatcher
    ): Flow<NetworkFetcher<List<Equipments>>> = flow {
        emit(Loading)
        val result = dataSource.getEquipments()
        emit(result)
    }.flowOn(dispatcher)
}