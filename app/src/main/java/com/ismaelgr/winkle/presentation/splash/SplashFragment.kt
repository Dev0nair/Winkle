package com.ismaelgr.winkle.presentation.splash

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : BaseFragment(R.layout.fragment_splash), SplashContract.View {

    private lateinit var splashPresenter: SplashContract.Presenter

    override fun initElements() {
        splashPresenter = SplashPresenter(this as SplashContract.View)
    }
}
