package ru.dariamikhailukova.cardsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ru.dariamikhailukova.cardsgame.databinding.ActivityMainBinding
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository
import java.util.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookException

import com.facebook.login.LoginResult

import com.facebook.FacebookCallback


import com.facebook.login.widget.LoginButton

import com.facebook.CallbackManager
import com.facebook.CallbackManager.Factory.create
import com.google.firebase.auth.FirebaseAuth


//1F:B3:F5:46:04:A7:04:19:18:37:22:C3:AE:8F:04:FA:43:B2:EB:12
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FacebookSdk.sdkInitialize(this)

        val repository = HeroesRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setSupportActionBar(findViewById(R.id.myToolbar))

        setGoogleAuth()
        setBottomNavigation()
    }

    private fun setGoogleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setBottomNavigation() {
        val bottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.settingsFragment || destination.id == R.id.startFragment
                || destination.id == R.id.showHeroFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

}