package castelles.com.github.api.api

import castelles.com.github.api.model.VisionCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VisionCardApi {

    @GET("visioncards/{id}")
    suspend fun getVisionCard(@Path("id") id: Int): Response<VisionCard>

    @GET("visioncards/all")
    suspend fun getVisionCards(): Response<List<VisionCard>>
}