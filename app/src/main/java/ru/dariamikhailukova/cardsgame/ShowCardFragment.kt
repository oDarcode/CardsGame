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
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.BASE_URL_PICTURES
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.MINUS
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.ZERO
import ru.dariamikhailukova.cardsgame.util.changeEmptyToString

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

        setImages()
        setHtmlText()
        setText()

        return binding.root
    }

    private fun setImages() {
        Glide.with(this)
            .load(getString(R.string.link, BASE_URL_PICTURES, args.cardInfo.cardId))
            .override(884, 1318)
            .into(binding.cImage)

        Glide.with(this)
            .load(getString(R.string.link, BASE_URL_PICTURES, args.cardInfo.heroID))
            .override(892, 766)
            .into(binding.cImageSmt)
    }

    private fun setHtmlText() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            var text = Html.fromHtml(getString(R.string.card_name, args.cardInfo.cardName), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cName.text =  Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)

            text = Html.fromHtml(args.cardInfo.cardText, Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cCardText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)

            text = Html.fromHtml(changeEmptyToString(args.cardInfo.flavorText), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.cFlavorText.text =  Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setText() {
        binding.cAttack.text = getString(R.string.attack, args.cardInfo.attack.toString())
        binding.cHealth.text = getString(R.string.health, args.cardInfo.health.toString())
        binding.cTechLevel.text = getString(R.string.tech_level, args.cardInfo.techLevel.toString())
        binding.cCardRace.text = getString(R.string.card_race, args.cardInfo.cardRace)
        binding.cCardText2.text = getString(R.string.card_text)
        binding.cRate.text = getString(R.string.win_rate, args.cardInfo.winRate.toString())
        binding.cPosition.text = getString(R.string.average_position, args.cardInfo.averagePosition?.toString() ?: ZERO)
        binding.cHeroName.text = getString(R.string.hero_choose, changeEmptyToString(args.cardInfo.heroName))
        binding.cFlavorText2.text =  getString(R.string.flavor_text)
    }

}