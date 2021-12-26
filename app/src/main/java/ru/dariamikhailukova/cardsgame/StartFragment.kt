package ru.dariamikhailukova.cardsgame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ru.dariamikhailukova.cardsgame.databinding.FragmentSettingsBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.openButton.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_startFragment_to_heroesFragment) }
        return view
    }
}