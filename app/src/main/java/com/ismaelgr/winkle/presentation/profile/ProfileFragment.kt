package com.ismaelgr.winkle.presentation.profile

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileContract.View {

    private val profilePresenter: ProfileContract.Presenter by inject<ProfilePresenter> {
        parametersOf(
            this
        )
    }

    override fun initElements() {
    }

    override fun bindPresenter(): BaseContract.Presenter = this.profilePresenter
}
