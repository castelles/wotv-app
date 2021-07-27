package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.Equipments
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {

    suspend fun getEquipment(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<Equipments>>

    suspend fun getEquipments(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<Equipments>>>
}