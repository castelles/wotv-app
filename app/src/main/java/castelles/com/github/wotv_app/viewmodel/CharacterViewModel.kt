package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.Character
import castelles.com.github.api.repository.contract.CharacterRepository
import castelles.com.github.wotv_app.viewmodel.contract.BaseViewModel
import castelles.com.github.wotv_app.viewmodel.contract.ViewModelListContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class CharacterViewModel(
    private val repository: CharacterRepository
) : BaseViewModel<Character>(MAX_PER_PAGE_CHAR) {

    fun getCharacters() {
        viewModelScope.launch {
            repository.getCharacters().collect {
                _viewState.value = _viewState.value.copy(it)
            }
        }
    }

    override fun search(value: String) {
        if (value.isEmpty()) {
            updateList(getOffset())
        } else {
            items.clear()
            items.addAll(allItems.filter {
                it.name.toLowerCase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            })
        }
    }

    companion object {
        const val MAX_PER_PAGE_CHAR = 14
    }
}