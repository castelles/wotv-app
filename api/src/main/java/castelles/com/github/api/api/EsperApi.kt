package castelles.com.github.api.api

import castelles.com.github.api.model.Esper
import castelles.com.github.api.model.VisionCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EsperApi {

    @GET("espers/{id}")
    suspend fun getEsper(@Path("id") id: Int): Response<Esper>

    @GET("espers/all")
    suspend fun getEspers(): Response<List<Esper>>

}