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
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException





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
        return view
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(activity as MainActivity)
        if (account != null) {
            updateUI(account)
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
}