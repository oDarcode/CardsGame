package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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

        binding.hName.text = getString(R.string.hero_name, args.heroInfo.heroName)
        binding.hRate.text = getString(R.string.win_rate, args.heroInfo.winRate.toString())
        binding.hPosition.text = getString(R.string.average_position, args.heroInfo.averagePosition.toString())
        binding.hSelection.text = getString(R.string.selection_frequency, args.heroInfo.selectionFrequency.toString())
        binding.hRating.text = getString(R.string.rating_change, args.heroInfo.ratingChange.toString())
        binding.hHealth.text = getString(R.string.health, args.heroInfo.health.toString())

        binding.hPowerName.text = getString(R.string.hero_power_name, args.heroInfo.heroPowerName)
        binding.hPowerText.text = getString(R.string.hero_power_text, args.heroInfo.heroPowerText)
        binding.hPowerCost.text = getString(R.string.hero_power_cost, args.heroInfo.heroPowerCost.toString())

        return binding.root
    }

}