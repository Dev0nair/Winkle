package com.ismaelgr.winkle.presentation.signin

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : BaseFragment(R.layout.fragment_signin), SignInContract.View {

    private lateinit var signinPresenter: SignInContract.Presenter

    override fun initElements() {
        signinPresenter = SignInPresenter(this as SignInContract.View)
    }
}
