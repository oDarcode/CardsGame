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

        binding.cName.text = getString(R.string.card_name, args.cardInfo.cardName)
        binding.cAttack.text = getString(R.string.attack, args.cardInfo.attack.toString())
        binding.cHealth.text = getString(R.string.health, args.cardInfo.health.toString())
        binding.cTechLevel.text = getString(R.string.tech_level, args.cardInfo.techLevel.toString())
        binding.cCardRace.text = getString(R.string.card_race, args.cardInfo.cardRace)
        binding.cCardText.text = getString(R.string.card_text, args.cardInfo.cardText)
        binding.cRate.text = getString(R.string.win_rate, args.cardInfo.winRate.toString())
        binding.cPosition.text = getString(R.string.average_position, args.cardInfo.averagePosition.toString())
        binding.cHeroName.text = getString(R.string.hero_choose, args.cardInfo.heroName)

        binding.cFlavorText.text = getString(R.string.flavor_text, args.cardInfo.flavorText)

        return binding.root
    }

}