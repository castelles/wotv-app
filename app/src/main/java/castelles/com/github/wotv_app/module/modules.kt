package castelles.com.github.wotv_app.module

import castelles.com.github.api.datasource.CharacterBuildDataSource
import castelles.com.github.api.datasource.UserDataSource
import castelles.com.github.api.repository.CharacterBuildRepositoryImpl
import castelles.com.github.api.repository.UserRepositoryImpl
import castelles.com.github.api.repository.contract.CharacterBuildRepository
import castelles.com.github.api.repository.contract.UserRepository
import castelles.com.github.wotv_app.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val dataSourceModules = module {
    single { UserDataSource() }
    single { CharacterBuildDataSource() }
}

val repositoryModules = module {
    single { UserRepositoryImpl(get()) } bind UserRepository::class
    single { CharacterBuildRepositoryImpl(get()) } bind CharacterBuildRepository::class
}

val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
}