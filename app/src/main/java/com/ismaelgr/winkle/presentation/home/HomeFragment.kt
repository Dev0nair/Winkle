package com.ismaelgr.winkle.presentation.home

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var homePresenter: HomeContract.Presenter

    override fun initElements() {
        homePresenter = HomePresenter(this as HomeContract.View)
    }
}
