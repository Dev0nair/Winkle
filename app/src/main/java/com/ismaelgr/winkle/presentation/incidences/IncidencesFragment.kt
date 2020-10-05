package com.ismaelgr.winkle.presentation.incidences

import androidx.fragment.app.Fragment
import com.ismaelgr.winkle.presentation.base.BaseFragment
import com.ismaelgr.winkle.R

/**
 * A simple [Fragment] subclass.
 */
class IncidencesFragment : BaseFragment(R.layout.fragment_incidences), IncidencesContract.View {

    private lateinit var incidencesPresenter: IncidencesContract.Presenter

    override fun initElements() {
        incidencesPresenter = IncidencesPresenter(this as IncidencesContract.View)
    }
}
