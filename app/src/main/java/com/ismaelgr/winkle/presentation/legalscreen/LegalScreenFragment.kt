package com.ismaelgr.winkle.presentation.legalscreen

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.domain.usecase.LegalConfirmationUseCase
import kotlinx.android.synthetic.main.fragment_legalscreen.*

/**
 * A simple [Fragment] subclass.
 */
class LegalScreenFragment : BaseFragment(R.layout.fragment_legalscreen), LegalScreenContract.View {

    private lateinit var legalscreenPresenter: LegalScreenContract.Presenter

    override fun loadLogin() {
        findNavController().navigate(R.id.action_legalScreenFragment_to_loginFragment)
    }

    override fun initElements() {
        legalscreenPresenter =
            LegalScreenPresenter(this as LegalScreenContract.View, LegalConfirmationUseCase())

        legal_accept_btn.setOnClickListener { legalscreenPresenter.onAcceptButtonClick() }
    }
}
