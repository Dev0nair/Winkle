package com.ismaelgr.winkle.presentation.splash

import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.data.repository.factory.AccountRepositoryFactory
import com.ismaelgr.winkle.data.repository.factory.ProfileRepositoryFactory
import com.ismaelgr.winkle.domain.usecase.HasProfileUseCase
import com.ismaelgr.winkle.domain.usecase.IsUserLoggedUseCase
import com.ismaelgr.winkle.domain.usecase.LegalConfirmationUseCase
import com.ismaelgr.winkle.presentation.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : BaseFragment(R.layout.fragment_splash), SplashContract.View {

    private lateinit var splashPresenter: SplashContract.Presenter

    override fun initElements() {
        splashPresenter = SplashPresenter(
            this as SplashContract.View,
            LegalConfirmationUseCase(),
            IsUserLoggedUseCase(AccountRepositoryFactory().getRepository()),
            HasProfileUseCase(
                AccountRepositoryFactory().getRepository(),
                ProfileRepositoryFactory().getRepository()
            )
        )

        splashPresenter.onInitElements()
        Log.i("Splash Fragment", "Se han iniciado los elementos")
    }

    override fun loadLegal() {
        findNavController().navigate(R.id.action_splashFragment_to_legalScreenFragment)
    }

    override fun loadLogin() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    override fun loadMainApplication() {
        findNavController().navigate(R.id.action_splashFragment_to_flujo_rincipal)
    }

    override fun loadSignInProfile() {
        findNavController().navigate(R.id.action_splashFragment_to_signIn2Fragment)
    }

    override fun loadAnimation(onTimeCompleted: () -> Unit, timeToProceed: Long) {
        Handler().postDelayed(onTimeCompleted, timeToProceed)

        // Lógica de animación
    }
}
