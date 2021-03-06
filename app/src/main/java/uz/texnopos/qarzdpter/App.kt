package uz.texnopos.qarzdpter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.texnopos.qarzdpter.di.dataModule
import uz.texnopos.qarzdpter.di.helper
import uz.texnopos.qarzdpter.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, helper, viewModelModule)
        startKoin {//use AndroidLogger as Koin Logger - default Level
            androidLogger()
            //use the Android context given there
            androidContext(this@App)
            //load properties from assets/koin.properties file
            androidFileProperties()
            //module list
            koin.loadModules(modules)
        }
    }
}