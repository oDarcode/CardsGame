package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ru.dariamikhailukova.cardsgame.databinding.FragmentHeroesBinding
import java.util.Observer


class HeroesFragment : Fragment() {
    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_settings){
            Navigation.findNavController(binding.root).navigate(R.id.action_heroesFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}