package dojo.mkt.sample01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dojo.mkt.sample01.di.applicationModulesScope
import dojo.mkt.sample01.ui.ATMViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LOG#MainActivity", "deu onCreate da activity")

        loadKoinModules(applicationModulesScope)


    }
}