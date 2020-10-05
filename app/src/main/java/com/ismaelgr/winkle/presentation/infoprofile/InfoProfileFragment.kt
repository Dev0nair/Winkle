package com.ismaelgr.winkle.presentation.infoprofile

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class InfoProfileFragment : BaseFragment(R.layout.fragment_infoprofile), InfoProfileContract.View {

    private lateinit var infoprofilePresenter: InfoProfileContract.Presenter

    override fun initElements() {
        infoprofilePresenter = InfoProfilePresenter(this as InfoProfileContract.View)
    }
}
