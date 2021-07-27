package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.Equipments
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface VisionCardRepository {

    suspend fun getVisionCard(
        id: Int,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<VisionCard>>

    suspend fun getVisionCards(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<NetworkFetcher<List<VisionCard>>>

}