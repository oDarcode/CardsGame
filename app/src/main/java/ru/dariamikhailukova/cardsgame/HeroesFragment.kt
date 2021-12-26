package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dariamikhailukova.cardsgame.adapter.HeroAdapter
import ru.dariamikhailukova.cardsgame.databinding.FragmentHeroesBinding


class HeroesFragment : Fragment() {
    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private val myAdapter by lazy { HeroAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        mainActivity = activity as MainActivity
        setupRecyclerView()

        mainActivity.viewModel.getHeroes()
        mainActivity.viewModel.heroesResponse.observe(mainActivity, androidx.lifecycle.Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(mainActivity, response.code(), Toast.LENGTH_SHORT).show()
            }

        })

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

    private fun setupRecyclerView() {
        binding.recyclerViewHeroes.adapter = myAdapter
        binding.recyclerViewHeroes.layoutManager = LinearLayoutManager(mainActivity)
    }

}