package dojo.mkt.sample01

import android.app.Application
import android.util.Log
import dojo.mkt.sample01.di.applicationModulesScope
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("LOG#Application", "deu start do koin")

        startKoin {
            modules(applicationModulesScope)
        }
    }

}