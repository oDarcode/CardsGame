package ru.dariamikhailukova.cardsgame

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowHeroBinding
import ru.dariamikhailukova.cardsgame.util.Constants
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.BASE_URL_PICTURES
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.ZERO

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

        setImages()
        setHtmlText()
        setText()

        return binding.root
    }

    private fun setImages() {
        Glide.with(this)
            .load(getString(R.string.link, BASE_URL_PICTURES, args.heroInfo.heroId))
            .override(892, 766)
            .into(binding.hHeroImage)

        Glide.with(this)
            .load(getString(R.string.link, BASE_URL_PICTURES, args.heroInfo.heroPowerID))
            .override(882, 1314)
            .into(binding.hPowerImage)
    }

    private fun setHtmlText() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val text = Html.fromHtml(args.heroInfo.heroPowerText, Html.FROM_HTML_MODE_LEGACY).toString()
            binding.hPowerText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setText() {
        binding.hName.text = getString(R.string.hero_name, args.heroInfo.heroName)
        binding.hRate.text = getString(R.string.win_rate, args.heroInfo.winRate.toString())
        binding.hPosition.text = getString(R.string.average_position, args.heroInfo.averagePosition?.toString() ?: ZERO)
        binding.hSelection.text = getString(R.string.selection_frequency, args.heroInfo.selectionFrequency?.toString() ?: ZERO)
        binding.hRating.text = getString(R.string.rating_change, args.heroInfo.ratingChange?.toString() ?: ZERO)
        binding.hHealth.text = getString(R.string.health, args.heroInfo.health.toString())
        binding.hPowerName.text = getString(R.string.hero_power_name, args.heroInfo.heroPowerName)
        binding.hPowerText2.text = getString(R.string.hero_power_text)
        binding.hPowerCost.text = getString(R.string.hero_power_cost, powerCostToString(args.heroInfo.heroPowerCost))
    }

    private fun powerCostToString(number: Int): String {
        return if (number == -1) {
            getString(R.string.passive)
        } else {
            number.toString()
        }
    }
}