package ru.dariamikhailukova.cardsgame

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowCardBinding
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.BASE_URL

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

        Glide.with(this)
            .load(getString(R.string.link, BASE_URL, args.cardInfo.cardId))
            .override(884, 1318)
            .into(binding.cImage)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val text = Html.fromHtml(getString(R.string.card_name, args.cardInfo.cardName), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cName.text =  Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            val text = Html.fromHtml(getString(R.string.card_name, args.cardInfo.cardName)).toString()
            binding.cName.text =  Html.fromHtml(text)
        }
        binding.cAttack.text = getString(R.string.attack, args.cardInfo.attack.toString())
        binding.cHealth.text = getString(R.string.health, args.cardInfo.health.toString())
        binding.cTechLevel.text = getString(R.string.tech_level, args.cardInfo.techLevel.toString())
        binding.cCardRace.text = getString(R.string.card_race, args.cardInfo.cardRace)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val text = Html.fromHtml(getString(R.string.card_text, args.cardInfo.cardText), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cCardText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            val text = Html.fromHtml(getString(R.string.card_text, args.cardInfo.cardText)).toString()
            binding.cCardText.text = Html.fromHtml(text)
        }
        binding.cRate.text = getString(R.string.win_rate, args.cardInfo.winRate.toString())
        binding.cPosition.text = getString(R.string.average_position, args.cardInfo.averagePosition.toString())
        binding.cHeroName.text = getString(R.string.hero_choose, args.cardInfo.heroName)

        Glide.with(this)
            .load(getString(R.string.link, BASE_URL, args.cardInfo.heroID))
            .override(892, 766)
            .into(binding.cImageSmt);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val text = Html.fromHtml(getString(R.string.flavor_text, args.cardInfo.flavorText), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cFlavorText.text =  Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            val text = Html.fromHtml(getString(R.string.flavor_text, args.cardInfo.flavorText)).toString()
            binding.cFlavorText.text =  Html.fromHtml(text)
        }

        return binding.root
    }

}