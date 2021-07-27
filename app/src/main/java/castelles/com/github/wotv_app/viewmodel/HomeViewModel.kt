package castelles.com.github.wotv_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.api.model.CharacterBuildResponse
import castelles.com.github.api.model.User
import castelles.com.github.api.model.Waterfall
import castelles.com.github.api.repository.contract.CharacterBuildRepository
import castelles.com.github.api.repository.contract.UserRepository
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharacterBuildRepository
): ViewModel() {

    inline fun getBuilds(
        crossinline action: suspend (NetworkFetcher<List<CharacterBuildResponse>>) -> Unit
    ) {
        viewModelScope.launch {
            createUser().collect { action.invoke(it) }
        }
    }

    suspend fun createUser() = repository.getBuilds(1)

}