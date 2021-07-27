package castelles.com.github.api.api

import castelles.com.github.api.model.Character
import castelles.com.github.api.model.Equipments
import castelles.com.github.api.model.JobResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

    @GET("characters/{id}/jobs")
    suspend fun getCharacterJobs(@Path("id") id: Int): Response<List<JobResponse>>

    @GET("characters/all")
    suspend fun getCharacters(): Response<List<Character>>
}