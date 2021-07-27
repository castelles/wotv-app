package castelles.com.github.api.datasource

import castelles.com.github.api.api.EquipmentApi
import castelles.com.github.api.datasource.model.DataSource

class EquipmentDataSource: DataSource() {

    private val api = retrofit.create(EquipmentApi::class.java)

    suspend fun getEquipment(id: Int) = getResponse(api.getEquipment(id))

    suspend fun getEquipments() = getResponse(api.getEquipments())
}