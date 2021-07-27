package castelles.com.github.wotv_app.module

import castelles.com.github.api.datasource.*
import castelles.com.github.api.repository.*
import castelles.com.github.api.repository.contract.*
import castelles.com.github.wotv_app.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val dataSourceModules = module {
    single { UserDataSource() }
    single { CharacterBuildDataSource() }
    single { CharacterDataSource() }
    single { EquipmentDataSource() }
    single { EsperDataSource() }
    single { VisionDataSource() }
}

val repositoryModules = module {
    single { UserRepositoryImpl(get()) } bind UserRepository::class
    single { CharacterBuildRepositoryImpl(get()) } bind CharacterBuildRepository::class
    single { CharacterRepositoryImpl(get()) } bind CharacterRepository::class
    single { EquipmentRepositoryImpl(get()) } bind EquipmentRepository::class
    single { EsperRepositoryImpl(get()) } bind EsperRepository::class
    single { VisionCardRepositoryImpl(get()) } bind VisionCardRepository::class
}

val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
}