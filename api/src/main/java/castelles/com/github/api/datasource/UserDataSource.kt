package castelles.com.github.api.datasource

import castelles.com.github.api.api.UserApi
import castelles.com.github.api.datasource.model.DataSource
import castelles.com.github.api.model.User

class UserDataSource: DataSource() {
    private val api = retrofit.create(UserApi::class.java)

    suspend fun createUser(user: User) = getResponse(api.createUser(user))
}