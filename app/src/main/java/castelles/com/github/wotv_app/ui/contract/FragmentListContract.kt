package castelles.com.github.wotv_app.ui.contract

import castelles.com.github.api.utils.ErrorHandler

interface FragmentListContract<T> {

    fun bindViewModel()
    fun setRecycler()
    fun loadList(items: List<T>?)
    fun onError(error: ErrorHandler<out Any?>)
    fun bindClicks()
    fun enablePageButtons(enable: Boolean = true)
    fun onSearchClick()

}