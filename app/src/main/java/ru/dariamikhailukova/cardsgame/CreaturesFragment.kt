package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import ru.dariamikhailukova.cardsgame.adapter.CardsAdapter
import ru.dariamikhailukova.cardsgame.databinding.FragmentCreaturesBinding

class CreaturesFragment : Fragment() {
    private var _binding: FragmentCreaturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private val cardsAdapter by lazy { CardsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreaturesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        mainActivity = activity as MainActivity
        setupRecyclerView()
        sendRequest()

        mainActivity.viewModel.cardsResponse.observe(mainActivity, androidx.lifecycle.Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { cardsAdapter.setData(it) }
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
            Navigation.findNavController(binding.root).navigate(R.id.action_creaturesFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCreatures.adapter = cardsAdapter
        binding.recyclerViewCreatures.layoutManager = LinearLayoutManager(mainActivity)
    }

    private fun sendRequest() {
        val googleUser = GoogleSignIn.getLastSignedInAccount(mainActivity)
        val facebookUser = FirebaseAuth.getInstance().currentUser
        if (googleUser != null) {
            mainActivity.viewModel.getCards(googleUser.id.toString())
        } else if (facebookUser != null) {
            mainActivity.viewModel.getCards(facebookUser.uid)
        } else {
            mainActivity.viewModel.getCards()
        }
    }

}