package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ru.dariamikhailukova.cardsgame.databinding.FragmentCreaturesBinding



class CreaturesFragment : Fragment() {
    private var _binding: FragmentCreaturesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreaturesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_settings){
            Navigation.findNavController(binding.root).navigate(R.id.action_creaturesFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}