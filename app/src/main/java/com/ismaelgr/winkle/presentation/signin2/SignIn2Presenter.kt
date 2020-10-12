package com.ismaelgr.winkle.presentation.signin2

import com.google.firebase.auth.FirebaseAuth
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.entity.Perfil
import com.ismaelgr.winkle.domain.usecase.CreateProfileUseCase
import com.ismaelgr.winkle.presentation.base.BasePresenter

class SignIn2Presenter(
    private val signin2: SignIn2Contract.View,
    private val createProfileUseCase: CreateProfileUseCase
) :
    BasePresenter<SignIn2Contract.View>(signin2), SignIn2Contract.Presenter {

    override fun onSignInClick(
        username: String,
        contactEmail: String,
        contactPhone: String,
        description: String
    ) {
        if (username.isNotEmpty()) {
            val perfil = Perfil(
                id = "",
                email = "",
                emailContacto = contactEmail,
                descripcion = description,
                image = "",
                username = username,
                telefono = contactPhone
            )

            showLoading(true)
            signin2.disableButtons()

            createProfileUseCase.execute(
                perfil,
                onSuccess = {
                    showLoading(false)
                    signin2.navigateMainApp()
                },
                onError = {
                    signin2.run {
                        showLoading(false)
                        showError(it)
                        enableButtons()
                    }
                }
            )
        } else {
            signin2.showError(signin2.getMyString(R.string.err_username_empty))
        }
    }

    override fun onBackClick() {
        signin2.navigateBack()
    }

    override fun onExitToLoginBtnClick() {
        FirebaseAuth.getInstance().signOut()
        signin2.backToLogin()
    }
}