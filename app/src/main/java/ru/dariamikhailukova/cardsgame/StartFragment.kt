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
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FacebookAuthProvider
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    var mCallbackManager: CallbackManager = CallbackManager.Factory.create()
    lateinit var mAuth: FirebaseAuth
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        mainActivity = activity as MainActivity
        mAuth = FirebaseAuth.getInstance()

        binding.openButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_startFragment_to_heroesFragment)
        }
        binding.authorizationButton.setOnClickListener { signIn() }

        binding.loginButton.setReadPermissions("email", "public_profile")
        binding.loginButton.registerCallback(
            mCallbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(result: LoginResult?) {
                    result?.accessToken?.let { handleFacebookAccessToken(it) }
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })

        /*viewModel.getHero()
        viewModel.heroResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("name ", response.body()?.name.toString())
                Log.d("health ", response.body()?.health.toString())
                Log.d("powerName ", response.body()?.powerName.toString())
                Log.d("powerText ", response.body()?.powerText.toString())
                Log.d("powerCost ", response.body()?.powerCost.toString())
                Log.d("powerId ", response.body()?.powerId.toString())
            } else {
                Log.d("error ", response.errorBody().toString())
            }

        })*/

        return view
    }

    override fun onStart() {
        super.onStart()
        updateUI(
            googleUser = GoogleSignIn.getLastSignedInAccount(mainActivity),
            facebookUser = mAuth.currentUser
        )
    }

    private fun signIn() {
        val signInIntent: Intent = mainActivity.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val googleUser = completedTask.getResult(ApiException::class.java)
            updateUI(googleUser = googleUser)
        } catch (e: ApiException) {
            presentError()
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    val facebookUser: FirebaseUser? = mAuth.currentUser
                    updateUI(facebookUser = facebookUser)
                } else {
                    presentError()
                }
            }
    }

    private fun updateUI(googleUser: GoogleSignInAccount? = null, facebookUser: FirebaseUser? = null) {
        if (googleUser != null || facebookUser != null) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_startFragment_to_heroesFragment)
        }
    }

    private fun presentError() {
        Toast.makeText(mainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val RC_SIGN_IN = 0
    }
}