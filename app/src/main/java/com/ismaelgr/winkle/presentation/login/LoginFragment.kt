package com.ismaelgr.winkle.presentation.login

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.LoginUseCase
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login), LoginContract.View {

    private lateinit var loginPresenter: LoginContract.Presenter

    override fun initElements() {
        loginPresenter = LoginPresenter(this as LoginContract.View, LoginUseCase())

        edit_email.setOnFocusChangeListener(::onFocusListener)
        edit_pass.setOnFocusChangeListener(::onFocusListener)

        btn_login.setOnClickListener {
            loginPresenter.onLoginBtnClick(
                edit_email.text.toString(),
                edit_pass.text.toString()
            )
        }
    }

    override fun loadMainApplication() {
        findNavController().navigate(R.id.action_loginFragment_to_flujo_principal)
    }

    private fun onFocusListener(view: View, hasFocus: Boolean) {
        if (!hasFocus) hideKeyboard()
    }
}
