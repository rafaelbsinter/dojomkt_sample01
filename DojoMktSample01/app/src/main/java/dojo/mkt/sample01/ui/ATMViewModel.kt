package dojo.mkt.sample01.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import dojo.mkt.sample01.Application
import dojo.mkt.sample01.use_case.ATMUseCase

class ATMViewModel(
    val atmUseCase: ATMUseCase,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {


}