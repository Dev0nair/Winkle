package com.ismaelgr.winkle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var appBarConfiguration2: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFirebase()

        configurePrincipalNavigation()
    }

    private fun configurePrincipalNavigation() {
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.layout.fragment_splash,
                R.layout.fragment_legalscreen,
                R.layout.fragment_main
            )
        )

        appBarConfiguration2 = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.layout.fragment_home,
                R.layout.fragment_myproducts,
                R.layout.fragment_shoplist,
                R.layout.fragment_profile
            )
        )
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this).run {
            Log.i("MAIN", "Se ha inicializado firebase!")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        secondary_nav_host?.let { secondaryHost ->
            return secondaryHost.findNavController()
                .navigateUp(appBarConfiguration2) || super.onSupportNavigateUp()
        }

        val navController = findNavController(R.id.principal_nav_host)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
