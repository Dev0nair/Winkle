package com.ismaelgr.winkle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFirebase()
    }


    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this).run {
            Log.i("MAIN", "Se ha inicializado firebase!")
        }
    }
}
