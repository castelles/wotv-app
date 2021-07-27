package castelles.com.github.api.utils

sealed class NetworkFetcher<out T>

data class Success<T>(val data: T?) : NetworkFetcher<T>()

data class Error(val error: Throwable): NetworkFetcher<Nothing>()

object Loading: NetworkFetcher<Nothing>()
