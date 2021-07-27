package castelles.com.github.api.datasource

import castelles.com.github.api.api.VisionCardApi
import castelles.com.github.api.datasource.model.DataSource

class VisionDataSource: DataSource() {

    private val api = retrofit.create(VisionCardApi::class.java)

    suspend fun getVisionCard(id: Int) = getResponse(api.getVisionCard(id))

    suspend fun getVisionCards() = getResponse(api.getVisionCards())
}