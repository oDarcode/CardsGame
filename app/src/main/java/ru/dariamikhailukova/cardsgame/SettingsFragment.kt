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
        val currentUser = mAuth.currentUser
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id

            binding.name.text = personName.toString()
            binding.email.text = personId.toString()
        } else if (currentUser != null) {
            val personName = currentUser.displayName
            val personEmail = currentUser.email
            val personId = currentUser.uid

            binding.name.text = personName.toString()
            binding.email.text = personId.toString()
        }


        binding.exit.setOnClickListener {
            exitApplication()
        }
        return view
    }

    private fun exitApplication() {
        if (GoogleSignIn.getLastSignedInAccount(mainActivity) != null) {
            signOutGoogle()
        } else if (mAuth.currentUser != null) {
            signOutFacebook()
        } else {
            Toast.makeText(mainActivity, "You are not log in", Toast.LENGTH_SHORT).show()
        }
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
        Toast.makeText(mainActivity, "We are log out", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_settingsFragment_to_startFragment)
    }

}