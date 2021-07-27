package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.CharacterBuildResponse
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.repository.contract.CharacterBuildRepository
import castelles.com.github.api.repository.contract.VisionCardRepository
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: VisionCardRepository
): ViewModel() {

    inline fun getBuilds(
        crossinline action: suspend (NetworkFetcher<List<VisionCard>>) -> Unit
    ) {
        viewModelScope.launch {
            requestBuilds().collect { action.invoke(it) }
        }
    }

    suspend fun requestBuilds() = repository.getVisionCards()

}