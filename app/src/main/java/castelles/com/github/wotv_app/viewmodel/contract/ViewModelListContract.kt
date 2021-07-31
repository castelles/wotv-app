package castelles.com.github.wotv_app.viewmodel.contract

interface ViewModelListContract<T> {

    fun addToList(_items: List<T>)
    fun page(forward: Boolean, firstPage: Boolean = false)
    fun updateList(offset: Int)
    fun getOffset(): Int
}