package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dariamikhailukova.cardsgame.databinding.FragmentCreaturesBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentSettingsBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowHeroBinding

class ShowHeroFragment : Fragment() {
    private var _binding: FragmentShowHeroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowHeroBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

}