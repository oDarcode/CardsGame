package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import ru.dariamikhailukova.cardsgame.databinding.FragmentBattleTagBinding
import ru.dariamikhailukova.cardsgame.model.UserInfo
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.FACEBOOK
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.GOOGLE

class BattleTagFragment : Fragment() {
    private var _binding: FragmentBattleTagBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    lateinit var mAuth: FirebaseAuth
    private lateinit var personId: String
    private lateinit var system: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBattleTagBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        mAuth = FirebaseAuth.getInstance()
        getAutorisationName()

        binding.enterButton.setOnClickListener {
            if (binding.battleTagEnter.text.contains("#", ignoreCase = true) && correctBattleTag(binding.battleTagEnter.text.toString())) {
                val text = mainActivity.viewModel.battleTag
                mainActivity.viewModel.postUser(
                    UserInfo(
                        battleTag = mainActivity.viewModel.battleTag.orEmpty(),
                        stringedAuthorizationID = personId,
                        authorizationType = system
                    )
                )
            }
        }

        binding.exitButton2.setOnClickListener {
            exitApplication()
            mainActivity.finish()
        }

        mainActivity.viewModel.userResponse.observe(mainActivity, androidx.lifecycle.Observer { response ->
            if (response.isSuccessful) {
                Navigation.findNavController(binding.root).navigate(R.id.action_battleTagFragment_to_heroesFragment)
            } else {
                Toast.makeText(mainActivity, "Неправильный battle tag", Toast.LENGTH_SHORT).show()
            }
        })

        setHasOptionsMenu(true)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    private fun correctBattleTag(str: String): Boolean {
        val parts = str.split("#")

        if (parts.size == 2 && parts[0].isNotEmpty() && parts[1].isNotEmpty()) {
            mainActivity.viewModel.battleTag = getString(R.string.silly_back, parts[0], parts[1])
            return true
        }
        return false
    }

    private fun exitApplication() {
        if (GoogleSignIn.getLastSignedInAccount(mainActivity) != null) {
            (mainActivity).mGoogleSignInClient.signOut()
        } else {
            mAuth.signOut()
            LoginManager.getInstance().logOut()
        }
    }

    private fun getAutorisationName() {
        val acct = GoogleSignIn.getLastSignedInAccount(mainActivity)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (acct != null) {
            personId = acct.id.toString()
            system = GOOGLE
        } else if (currentUser != null) {
            personId = currentUser.uid
            system = FACEBOOK
        }
    }

}