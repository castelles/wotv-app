package castelles.com.github.api.datasource

import castelles.com.github.api.api.CharacterBuilderApi
import castelles.com.github.api.datasource.model.DataSource
import castelles.com.github.api.model.CharacterBuildDto

class CharacterBuildDataSource: DataSource() {

    private val api = retrofit.create(CharacterBuilderApi::class.java)

    suspend fun buildTeam(team: CharacterBuildDto)
        = getResponse(api.createBuild(team))

    suspend fun getBuilds(id: Int) = getResponse(api.getUserBuilds(id))
}