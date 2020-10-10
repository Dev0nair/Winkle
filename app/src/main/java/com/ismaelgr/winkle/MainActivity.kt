package com.ismaelgr.winkle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

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
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this).run {
            Log.i("MAIN", "Se ha inicializado firebase!")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.principal_nav_host)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
