package com.ismaelgr.winkle.presentation.signin2

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class SignIn2Fragment : BaseFragment(R.layout.fragment_signin2), SignIn2Contract.View {

    private lateinit var signin2Presenter: SignIn2Contract.Presenter

    override fun initElements() {
        signin2Presenter = SignIn2Presenter(this as SignIn2Contract.View)
    }
}
