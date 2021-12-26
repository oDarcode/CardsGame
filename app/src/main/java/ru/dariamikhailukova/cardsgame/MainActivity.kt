package ru.dariamikhailukova.cardsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.dariamikhailukova.cardsgame.databinding.ActivityMainBinding
import ru.dariamikhailukova.cardsgame.repository.HeroesRepository


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = HeroesRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getHero()
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

        })

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
    }


}