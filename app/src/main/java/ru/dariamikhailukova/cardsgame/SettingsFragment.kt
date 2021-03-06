package ru.dariamikhailukova.cardsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import ru.dariamikhailukova.cardsgame.databinding.FragmentSettingsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import androidx.annotation.NonNull
import com.facebook.login.LoginManager

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    lateinit var mAuth: FirebaseAuth
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        mainActivity = activity as MainActivity
        mAuth = FirebaseAuth.getInstance()

        val acct = GoogleSignIn.getLastSignedInAccount(mainActivity)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (acct != null) {
            displayGoogle(acct)
        } else if (currentUser != null) {
            displayFacebook(currentUser)
        }
        binding.battleTag.text = mainActivity.viewModel.battleTag

        binding.exit.setOnClickListener {
            exitApplication()
        }
        return view
    }

    private fun exitApplication() {
        if (GoogleSignIn.getLastSignedInAccount(mainActivity) != null) {
            signOutGoogle()
            mainActivity.viewModel.battleTag = null
        } else if (mAuth.currentUser != null) {
            signOutFacebook()
            mainActivity.viewModel.battleTag = null
        } else {
            Toast.makeText(mainActivity, "???? ???? ????????????????????????", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayGoogle(acct: GoogleSignInAccount) {
        val personName = acct.displayName
        val personEmail = acct.email

        binding.name.text = personName.toString()
        binding.email.text = personEmail.toString()
    }

    private fun displayFacebook(currentUser: FirebaseUser) {
        val personName = currentUser.displayName
        val personEmail = currentUser.email

        binding.name.text = personName.toString()
        binding.email.text = personEmail.toString()
    }

    private fun signOutGoogle() {
        (mainActivity).mGoogleSignInClient.signOut()
        signOut()
    }

    private fun signOutFacebook() {
        mAuth.signOut()
        LoginManager.getInstance().logOut()
        signOut()
    }
    private fun signOut() {
        Toast.makeText(mainActivity, "???? ?????????? ???? ????????????????", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_settingsFragment_to_startFragment)
    }

}