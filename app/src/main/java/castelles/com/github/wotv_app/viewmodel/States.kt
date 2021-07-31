package castelles.com.github.wotv_app.viewmodel

import castelles.com.github.api.utils.NetworkFetcher

data class State<T>(
    val handler: NetworkFetcher<T>? = null
)