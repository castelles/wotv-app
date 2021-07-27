package castelles.com.github.api.api

import castelles.com.github.api.model.CharacterBuildDto
import castelles.com.github.api.model.CharacterBuildResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CharacterBuilderApi {

    @PUT("builders")
    suspend fun createBuild(@Body build: CharacterBuildDto): Response<Unit>

    @GET("builders/{userId}")
    suspend fun getUserBuilds(@Path("userId") id: Int): Response<List<CharacterBuildResponse>>
}