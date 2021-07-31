package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.repository.contract.VisionCardRepository
import castelles.com.github.wotv_app.viewmodel.contract.BaseViewModel
import castelles.com.github.wotv_app.viewmodel.contract.ViewModelListContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VisionCardViewModel(
    private val repository: VisionCardRepository
): BaseViewModel<VisionCard>(MAX_PER_PAGE_VISION) {

    fun getVisionCard() {
        viewModelScope.launch {
            repository.getVisionCards().collect {
                _viewState.value = _viewState.value.copy(it)
            }
        }
    }

    companion object {
        const val MAX_PER_PAGE_VISION = 18
    }
}