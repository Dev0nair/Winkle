package com.ismaelgr.winkle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFirebase()

        setupNavigation()

        configureNavigation()
    }

    private fun configureNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener(::onDestinationChanged)
    }

    private fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (isPrincipalDestination(destination.id)) {
            showBottomBar()
        } else {
            hideBottomBar()
        }
    }

    private fun isPrincipalDestination(destinationId: Int): Boolean {
        val menuPrincipal = PopupMenu(this, null).menu
        menuInflater.inflate(R.menu.bottom_navigation_menu, menuPrincipal)

        for (principalDest in menuPrincipal.iterator()) {
            if (principalDest.itemId == destinationId) {
                return true
            }
        }

        return false
    }

    private fun showBottomBar() {
        if (bottom_navigation_bar.visibility != View.VISIBLE) {
            bottom_navigation_bar.visibility = View.VISIBLE
        }
    }

    private fun hideBottomBar() {
        if (bottom_navigation_bar.visibility != View.GONE) {
            bottom_navigation_bar.visibility = View.GONE
        }
    }

    private fun setupNavigation() {
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.layout.fragment_splash,
                R.layout.fragment_legalscreen,
                R.layout.fragment_login,
                R.layout.fragment_home,
                R.layout.fragment_myproducts,
                R.layout.fragment_shoplist,
                R.layout.fragment_profile
            )
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottom_navigation_bar.setupWithNavController(navController)
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this).run {
            Log.i("MAIN", "Se ha inicializado firebase!")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
