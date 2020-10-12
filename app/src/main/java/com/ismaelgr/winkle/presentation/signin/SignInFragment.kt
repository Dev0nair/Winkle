package com.ismaelgr.winkle.presentation.signin

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.CreateAccountUseCase
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : BaseFragment(R.layout.fragment_signin), SignInContract.View {

    private lateinit var signinPresenter: SignInContract.Presenter

    override fun navigateNextSignIn() {
        findNavController().navigate(R.id.action_signInFragment_to_signIn2Fragment)
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }

    override fun initElements() {
        signinPresenter = SignInPresenter(this as SignInContract.View, CreateAccountUseCase())

        btn_continue1.setOnClickListener {
            signinPresenter.onContinuePressed(
                edit_email.text.toString(),
                edit_pass.text.toString()
            )
        }

        toolbar_back.setOnClickListener { signinPresenter.onBackPressed() }
    }
}
