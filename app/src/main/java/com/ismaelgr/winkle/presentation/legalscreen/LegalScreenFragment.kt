package com.ismaelgr.winkle.presentation.legalscreen

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class LegalScreenFragment : BaseFragment(R.layout.fragment_legalscreen), LegalScreenContract.View {

    private lateinit var legalscreenPresenter: LegalScreenContract.Presenter

    override fun initElements() {
        legalscreenPresenter = LegalScreenPresenter(this as LegalScreenContract.View)
    }
}
