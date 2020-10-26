package com.ismaelgr.winkle.presentation.profile

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseContract

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileContract.View {

    private lateinit var profilePresenter: ProfileContract.Presenter

    override fun initElements() {
        profilePresenter = ProfilePresenter(this as ProfileContract.View)
    }

    override fun bindPresenter(): BaseContract.Presenter = this.profilePresenter
}
