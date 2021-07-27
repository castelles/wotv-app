package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.Equipments
import castelles.com.github.api.model.Esper
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface EsperRepository {

    suspend fun getEsper(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<Esper>>

    suspend fun getEspers(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<Esper>>>

}