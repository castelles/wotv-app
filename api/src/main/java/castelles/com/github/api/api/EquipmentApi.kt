package castelles.com.github.api.api

import castelles.com.github.api.model.Equipments
import castelles.com.github.api.model.VisionCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EquipmentApi {

    @GET("equipments/{id}")
    suspend fun getEquipment(@Path("id") id: Int): Response<Equipments>

    @GET("equipments/all")
    suspend fun getEquipments(): Response<List<Equipments>>

}