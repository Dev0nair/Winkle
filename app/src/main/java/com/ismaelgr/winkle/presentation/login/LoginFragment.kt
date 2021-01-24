package com.ismaelgr.winkle.presentation.login

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseContract
import com.ismaelgr.winkle.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login), LoginContract.View {

    private val loginPresenter: LoginContract.Presenter by inject<LoginPresenter> {
        parametersOf(
            this as LoginContract.View
        )
    }

    override fun initElements() {
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

    override fun bindPresenter(): BaseContract.Presenter = this.loginPresenter

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
