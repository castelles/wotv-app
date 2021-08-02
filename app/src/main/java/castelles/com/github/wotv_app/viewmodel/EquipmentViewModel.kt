package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.Character
import castelles.com.github.api.model.Equipments
import castelles.com.github.api.repository.contract.EquipmentRepository
import castelles.com.github.wotv_app.viewmodel.contract.BaseViewModel
import castelles.com.github.wotv_app.viewmodel.contract.ViewModelListContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EquipmentViewModel(
    private val repository: EquipmentRepository
): BaseViewModel<Equipments>(MAX_PER_PAGE_EQUIPMENT) {

    fun getEquipments() {
        viewModelScope.launch {
            repository.getEquipments().collect {
                _viewState.value = _viewState.value.copy(it)
            }
        }
    }

    override fun search(value: String) {
        TODO("Not yet implemented")
    }

    companion object {
        const val MAX_PER_PAGE_EQUIPMENT = 12
    }

}