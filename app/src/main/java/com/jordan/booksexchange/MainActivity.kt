package com.jordan.booksexchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var navBottomBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navBottomBar = findViewById(R.id.bottom_nav)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            when(destination.id) {
                R.id.startFragment -> navBottomBar.visibility = View.GONE
                R.id.signInFragment -> navBottomBar.visibility = View.GONE
                R.id.signUpFragment -> navBottomBar.visibility = View.GONE
                else -> navBottomBar.visibility = View.VISIBLE
            }
        }

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment, R.id.postFragment, R.id.searchFragment, R.id.chatFragment,
            R.id.signInFragment , R.id.startFragment , R.id.signUpFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav.setupWithNavController(navController)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}
