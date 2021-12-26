package ru.dariamikhailukova.cardsgame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.dariamikhailukova.cardsgame.databinding.FragmentStartBinding
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.widget.Toast

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

import androidx.annotation.NonNull

import com.google.firebase.auth.FacebookAuthProvider

import com.google.firebase.auth.AuthCredential

import com.facebook.AccessToken
import com.google.android.gms.tasks.OnCompleteListener


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val RC_SIGN_IN = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.openButton.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_startFragment_to_heroesFragment) }
        binding.authorizationButton.setOnClickListener { signIn() }

        (activity as MainActivity).loginButton = binding.loginButton
        (activity as MainActivity).loginButton.setReadPermissions("email", "public_profile")
        (activity as MainActivity).loginButton.registerCallback((activity as MainActivity).mCallbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(result: LoginResult?) {
                result?.accessToken?.let { handleFacebookAccessToken(it) }
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(activity as MainActivity)
        if (account != null) {
            updateUI(account)
        }

        val currentUser = (activity as MainActivity).mAuth.currentUser
        if (currentUser != null) {
            updateU(currentUser)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            Navigation.findNavController(binding.root).navigate(R.id.action_startFragment_to_heroesFragment)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = (activity as MainActivity).mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Example", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        (activity as MainActivity).mAuth.signInWithCredential(credential)
            .addOnCompleteListener((activity as MainActivity)
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user: FirebaseUser? = (activity as MainActivity).mAuth.currentUser
                    updateU(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        (activity as MainActivity), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateU(null)
                }
            }
    }
    private fun updateU(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null) {
            Navigation.findNavController(binding.root).navigate(R.id.action_startFragment_to_heroesFragment)
        } else {
            Toast.makeText(activity as MainActivity, "Not user", Toast.LENGTH_SHORT).show()
        }
    }

}