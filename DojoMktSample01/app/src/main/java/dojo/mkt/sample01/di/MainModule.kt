package dojo.mkt.sample01.di

import dojo.mkt.sample01.ui.ATMViewModel
import dojo.mkt.sample01.use_case.ATMUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModulesScope by lazy {
    listOf(
       sampleModule
    )
}


private var sampleModule = module {

    viewModel { ATMViewModel(get(), get()) }

    factory { ATMUseCase() }
}