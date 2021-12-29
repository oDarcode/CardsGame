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
import retrofit2.Response
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
        getAuthorizationName()

        binding.enterButton.setOnClickListener {
            if (binding.battleTagEnter.text.contains("#", ignoreCase = true)
            ) {
                mainActivity.viewModel.battleTag = binding.battleTagEnter.text.toString()
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
                toastForErrors(response)
            }
        })

        setHasOptionsMenu(true)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    private fun toastForErrors(response: Response<String>) {
        if (response.code() == 404) {
            Toast.makeText(mainActivity, "Пользователь не найден", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mainActivity, "Неправильные параметры", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAuthorizationName() {
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

    private fun exitApplication() {
        if (GoogleSignIn.getLastSignedInAccount(mainActivity) != null) {
            (mainActivity).mGoogleSignInClient.signOut()
        } else {
            mAuth.signOut()
            LoginManager.getInstance().logOut()
        }
    }

}