package com.ismaelgr.winkle.presentation.main

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment(R.layout.fragment_main), MainContract.View {

    private lateinit var mainPresenter: MainContract.Presenter

    override fun initElements() {
        mainPresenter = MainPresenter(this as MainContract.View)
    }
}
