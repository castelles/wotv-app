package castelles.com.github.api.datasource

import castelles.com.github.api.api.CharacterApi
import castelles.com.github.api.datasource.model.DataSource

class CharacterDataSource: DataSource() {

    private val api = retrofit.create(CharacterApi::class.java)

    suspend fun getCharacter(id: Int) = getResponse(api.getCharacter(id))

    suspend fun getCharacterJobs(id: Int) = getResponse(api.getCharacterJobs(id))

    suspend fun getCharacters() = getResponse(api.getCharacters())
}