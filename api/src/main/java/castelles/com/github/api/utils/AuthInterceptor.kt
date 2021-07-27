package castelles.com.github.api.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String?): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newBuilder = token?.let { tk ->
            original
                .newBuilder()
                .addHeader("Authorization", "Bearer $tk")
        }

        return chain.proceed(newBuilder?.build() ?: original)
    }

}