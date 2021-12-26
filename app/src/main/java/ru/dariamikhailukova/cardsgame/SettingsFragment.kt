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








class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        //bottomNavigationView.setOnApplyWindowInsetsListener(null)

        val acct = GoogleSignIn.getLastSignedInAccount(activity as MainActivity)
        val currentUser = (activity as MainActivity).mAuth.currentUser
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id

            binding.name.text = personName.toString()
            binding.email.text = personEmail.toString()
        } else if (currentUser != null) {
            val personName = currentUser.displayName
            val personEmail = currentUser.email

            binding.name.text = personName.toString()
            binding.email.text = personEmail.toString()
        }


        binding.exit.setOnClickListener {
            if (GoogleSignIn.getLastSignedInAccount(activity as MainActivity) != null || (activity as MainActivity).mAuth.currentUser != null) {
                signOut()
            }             else {
                Toast.makeText(activity as MainActivity, "You are not log in", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun signOut() {
        if (GoogleSignIn.getLastSignedInAccount(activity as MainActivity) != null) {
            (activity as MainActivity).mGoogleSignInClient.signOut()
        }
        if ((activity as MainActivity).mAuth.currentUser != null) {
            (activity as MainActivity).mAuth.signOut()
            LoginManager.getInstance().logOut()
        }
        Toast.makeText(activity as MainActivity, "We are log out", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).navigate(R.id.action_settingsFragment_to_startFragment)
    }

}