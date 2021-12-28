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
import ru.dariamikhailukova.cardsgame.databinding.FragmentShowHeroBinding
import ru.dariamikhailukova.cardsgame.util.Constants

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

        Glide.with(this)
            .load(getString(R.string.link, Constants.BASE_URL, args.heroInfo.cardId))
            .override(892, 766)
            .into(binding.hHeroImage)

        binding.hName.text = getString(R.string.hero_name, args.heroInfo.heroName)
        binding.hRate.text = getString(R.string.win_rate, args.heroInfo.winRate.toString())
        binding.hPosition.text = getString(R.string.average_position, args.heroInfo.averagePosition.toString())
        binding.hSelection.text = getString(R.string.selection_frequency, args.heroInfo.selectionFrequency.toString())
        binding.hRating.text = getString(R.string.rating_change, args.heroInfo.ratingChange.toString())
        binding.hHealth.text = getString(R.string.health, args.heroInfo.health.toString())

        Glide.with(this)
            .load(getString(R.string.link, Constants.BASE_URL, args.heroInfo.heroPowerID))
            .override(882, 1314)
            .into(binding.hPowerImage)

        binding.hPowerName.text = getString(R.string.hero_power_name, args.heroInfo.heroPowerName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val text = Html.fromHtml(getString(R.string.hero_power_text, args.heroInfo.heroPowerText), Html.FROM_HTML_MODE_LEGACY).toString()
            binding.hPowerText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            val text = Html.fromHtml(getString(R.string.hero_power_text, args.heroInfo.heroPowerText)).toString()
            binding.hPowerText.text = Html.fromHtml(text)
        }
        binding.hPowerCost.text = getString(R.string.hero_power_cost, args.heroInfo.heroPowerCost.toString())

        return binding.root
    }

}