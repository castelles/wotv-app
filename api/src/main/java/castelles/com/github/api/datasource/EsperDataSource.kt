package castelles.com.github.api.datasource

import castelles.com.github.api.api.EsperApi
import castelles.com.github.api.datasource.model.DataSource

class EsperDataSource: DataSource() {

    private val api = retrofit.create(EsperApi::class.java)

    suspend fun getEsper(id: Int) = getResponse(api.getEsper(id))

    suspend fun getEspers() = getResponse(api.getEspers())
}