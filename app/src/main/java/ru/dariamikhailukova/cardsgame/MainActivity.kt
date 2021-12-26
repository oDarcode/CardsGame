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
    private lateinit var viewModel: MainViewModel
    lateinit var mGoogleSignInClient: GoogleSignInClient
    var mCallbackManager: CallbackManager = create()
    lateinit var loginButton: LoginButton
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FacebookSdk.sdkInitialize(this)
        mAuth = FirebaseAuth.getInstance()
        // Initialize Facebook Login button
        //mCallbackManager =

        val repository = HeroesRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        /*viewModel.getHero()
        viewModel.heroResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("name ", response.body()?.name.toString())
                Log.d("health ", response.body()?.health.toString())
                Log.d("powerName ", response.body()?.powerName.toString())
                Log.d("powerText ", response.body()?.powerText.toString())
                Log.d("powerCost ", response.body()?.powerCost.toString())
                Log.d("powerId ", response.body()?.powerId.toString())
            } else {
                Log.d("error ", response.errorBody().toString())
            }

        })*/
        setSupportActionBar(findViewById(R.id.myToolbar))

        val bottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.settingsFragment || destination.id == R.id.startFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



// ...
    }

}