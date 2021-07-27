package castelles.com.github.wotv_app.app

import android.app.Application
import castelles.com.github.wotv_app.module.dataSourceModules
import castelles.com.github.wotv_app.module.repositoryModules
import castelles.com.github.wotv_app.module.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(dataSourceModules, repositoryModules, viewModelModules)
        }
    }
}