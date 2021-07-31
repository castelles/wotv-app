package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.Esper
import castelles.com.github.api.repository.contract.EsperRepository
import castelles.com.github.wotv_app.viewmodel.contract.BaseViewModel
import castelles.com.github.wotv_app.viewmodel.contract.ViewModelListContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EsperViewModel(
    private val repository: EsperRepository
): BaseViewModel<Esper>(MAX_PER_PAGE_ESPER) {

    fun getEspers() {
        viewModelScope.launch {
            repository.getEspers().collect {
                _viewState.value = _viewState.value.copy(it)
            }
        }
    }

    companion object {
        const val MAX_PER_PAGE_ESPER = 14
    }
}