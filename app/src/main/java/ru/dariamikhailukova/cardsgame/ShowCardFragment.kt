package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowCardBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowHeroBinding

class ShowCardFragment : Fragment() {
    private var _binding: FragmentShowCardBinding? = null
    private val binding get() = _binding!!

    private val args: ShowCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowCardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.cName.text = args.cardInfo.cardName
        binding.cText.text = args.cardInfo.cardText
        binding.cRating.text = args.cardInfo.winRate.toString()

        return binding.root
    }

}