package ru.dariamikhailukova.cardsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dariamikhailukova.cardsgame.databinding.ActivityMainBinding
import ru.dariamikhailukova.cardsgame.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openButton.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }
}