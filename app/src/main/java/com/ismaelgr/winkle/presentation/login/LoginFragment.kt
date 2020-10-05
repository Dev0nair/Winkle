package com.ismaelgr.winkle.presentation.login

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login), LoginContract.View {

    private lateinit var loginPresenter: LoginContract.Presenter

    override fun initElements() {
        loginPresenter = LoginPresenter(this as LoginContract.View)
    }
}
