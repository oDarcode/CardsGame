package ru.dariamikhailukova.cardsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ru.dariamikhailukova.cardsgame.databinding.ActivityMainBinding
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository
import com.facebook.FacebookSdk;

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

        setSupportActionBar(binding.myToolbar)

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
        val config = AppBarConfiguration(navController.graph)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.settingsFragment || destination.id == R.id.startFragment
                || destination.id == R.id.showHeroFragment || destination.id == R.id.showCardFragment
                || destination.id == R.id.battleTagFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
        binding.myToolbar.setupWithNavController(navController, config)
    }

}