package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ru.dariamikhailukova.cardsgame.databinding.FragmentCreaturesBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentSettingsBinding
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowHeroBinding

class ShowHeroFragment : Fragment() {
    private var _binding: FragmentShowHeroBinding? = null
    private val binding get() = _binding!!

    private val args: ShowHeroFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowHeroBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.hName.text = args.heroInfo.heroName
        binding.hRate.text = args.heroInfo.winRate.toString()
        binding.hPosition.text = args.heroInfo.averagePosition.toString()
        binding.hSelection.text = args.heroInfo.selectionFrequency.toString()
        binding.hRating.text = args.heroInfo.ratingChange.toString()
        binding.hHealth.text = args.heroInfo.health.toString()

        binding.hPowerName.text = args.heroInfo.heroPowerName
        binding.hPowerText.text = args.heroInfo.heroPowerText
        binding.hPowerCost.text = args.heroInfo.heroPowerCost.toString()

        return binding.root
    }

}