package com.ismaelgr.winkle.presentation.login

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.HasProfileUseCase
import com.ismaelgr.winkle.domain.usecase.LoginUseCase
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login), LoginContract.View {

    private lateinit var loginPresenter: LoginContract.Presenter

    override fun initElements() {
        loginPresenter =
            LoginPresenter(this as LoginContract.View, LoginUseCase(), HasProfileUseCase())

        edit_email.setOnFocusChangeListener(::onFocusListener)
        edit_pass.setOnFocusChangeListener(::onFocusListener)

        btn_login.setOnClickListener {
            loginPresenter.onLogInBtnClick(
                edit_email.text.toString(),
                edit_pass.text.toString()
            )
        }

        btn_signin.setOnClickListener { loginPresenter.onSignInBtnClick() }
    }

    override fun loadMainApplication() {
        findNavController().navigate(R.id.action_loginFragment_to_flujo_principal)
    }

    override fun loadSignIn() {
        findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
    }

    override fun loadSignInProfile() {
        findNavController().navigate(R.id.action_loginFragment_to_signIn2Fragment)
    }

    override fun disableButtons() {
        btn_login.isEnabled = false
        btn_signin.isEnabled = false
    }

    override fun enableButtons() {
        btn_login.isEnabled = true
        btn_signin.isEnabled = true
    }

    private fun onFocusListener(view: View, hasFocus: Boolean) {
        if (!hasFocus) hideKeyboard()
    }
}
