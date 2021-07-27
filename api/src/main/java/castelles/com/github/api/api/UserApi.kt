package castelles.com.github.api.api

import castelles.com.github.api.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface UserApi {

    @PUT("users")
    suspend fun createUser(@Body user: User): Response<Unit>

}