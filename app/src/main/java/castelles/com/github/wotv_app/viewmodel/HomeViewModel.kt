package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.CharacterBuildResponse
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.repository.contract.CharacterBuildRepository
import castelles.com.github.api.repository.contract.VisionCardRepository
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.NetworkFetcher
import castelles.com.github.api.utils.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: VisionCardRepository
): ViewModel() {

    data class BuildsStates<T>(
        var handler: NetworkFetcher<T>? = null
    )

    private val _viewState = MutableStateFlow(BuildsStates<List<VisionCard>>())
    val viewState = _viewState.asStateFlow()

    fun getBuilds() {
//        viewModelScope.launch {
//            repository.getVisionCards().collect {
//                _viewState.value = _viewState.value.copy(it)
//            }
//        }
    }
}