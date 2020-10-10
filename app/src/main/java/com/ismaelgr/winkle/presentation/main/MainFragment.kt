package com.ismaelgr.winkle.presentation.main

import android.view.MenuInflater
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment(R.layout.fragment_main), MainContract.View {

    private lateinit var mainPresenter: MainContract.Presenter
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initElements() {
        mainPresenter = MainPresenter(this as MainContract.View)

        configureNavigation()
    }

    private fun configureNavigation() {
        val menu = PopupMenu(context, null).menu
        MenuInflater(context).inflate(R.menu.bottom_navigation_menu, menu)
        appBarConfiguration = AppBarConfiguration(menu)

        bottom_navigation_v.setOnNavigationItemSelectedListener {
            secondary_nav_host.findNavController().navigate(it.itemId)
            true
        }
    }
}
