package com.ismaelgr.winkle.presentation.signin2

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.CreateProfileUseCase
import kotlinx.android.synthetic.main.fragment_signin2.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class SignIn2Fragment : BaseFragment(R.layout.fragment_signin2), SignIn2Contract.View {

    private lateinit var signin2Presenter: SignIn2Contract.Presenter

    override fun navigateBack() {
        findNavController().popBackStack()
    }

    override fun navigateMainApp() {
        findNavController().navigate(R.id.action_signIn2Fragment_to_flujo_principal)
    }

    override fun initElements() {
        signin2Presenter = SignIn2Presenter(this as SignIn2Contract.View, CreateProfileUseCase())

        btn_continue.setOnClickListener {
            signin2Presenter.onSignInClick(
                username = edit_username.text.toString(),
                contactEmail = edit_contact_email.text.toString(),
                description = edit_description.text.toString(),
                contactPhone = edit_contact_phone.text.toString()
            )
        }
    }
}
