package dojo.mkt.sample01.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dojo.mkt.sample01.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ATMFragment : Fragment() {

    companion object {
        fun newInstance() = ATMFragment()
    }

    private val atmViewModel: ATMViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(atmViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.atm_fragment, container, false)
    }


}