package castelles.com.github.wotv_app.viewmodel.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import castelles.com.github.api.model.Character
import castelles.com.github.wotv_app.viewmodel.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T>(
    private val maxPerPage: Int
): ViewModel(), ViewModelListContract<T> {

    val items: MutableList<T> = mutableListOf()
    protected val allItems: MutableList<T> = mutableListOf()

    private var page = 0
    private var maxPages = 0

    protected val _viewState = MutableStateFlow(State<List<T>>())
    val viewState = _viewState.asStateFlow()

    private val _searchClick: MutableLiveData<Unit> = MutableLiveData()
    val searchClick: LiveData<Unit> = _searchClick

    override fun addToList(_items: List<T>) {
        allItems.apply {
            clear()
            addAll(_items)
            maxPages = allItems.size.div(maxPerPage)
            page(forward = true, firstPage = true)
        }
    }

    override fun page(forward: Boolean, firstPage: Boolean) {
        if (forward) {
            if (page < maxPages) {
                page = if (firstPage) page else page + 1
                val offset = page.times(maxPerPage)
                updateList(offset)
            }
        } else {
            if (page > 0) {
                page--
                val offset = page.times(maxPerPage)
                updateList(offset)
            }
        }
    }

    override fun updateList(offset: Int) {
        items.apply {
            clear()
            val rest = if (allItems.size - offset < maxPerPage)
                allItems.size - 1
            else offset + maxPerPage

            addAll(allItems.subList(offset, rest))
        }
    }

    override fun getOffset(): Int = page.times(maxPerPage)

    fun showSearch() {
        _searchClick.postValue(Unit)
    }

    abstract fun search(value: String)
}