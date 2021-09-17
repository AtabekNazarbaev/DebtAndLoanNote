package uz.texnopos.qarzdpter.ui.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.qarzdpter.R
import uz.texnopos.qarzdpter.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var b: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentSignInBinding.bind(view)
        b.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        b.btnSignIn.setOnClickListener {
            when {
                b.etEmail.text.toString().isEmpty() -> {
                    b.etEmail.error = "Введите email"
                }
                b.etPassword.text.toString().isEmpty() -> {
                    b.etPassword.error = "Введите пароль"
                }
                b.etPassword.text.toString().length < 6 -> {
                    b.etPassword.error = "Пароль должен состоять не менее 6 символов"
                }
                else -> {
                    viewModel.signIn(b.etEmail.text.toString(), b.etPassword.text.toString())
                }
            }
        }

        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
        } else {
            viewModel.signIn.observe(viewLifecycleOwner) {
                when (it) {
                    "loading" -> {
                        b.cconstrain.isVisible = false
                        b.tvSignInn.isVisible = false
                        b.loading.isVisible = true
                    }
                    "success" -> {
                        b.cconstrain.isVisible = true
                        b.tvSignInn.isVisible = true
                        b.loading.isVisible = false
                        findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                    }
                    else -> {
                        b.tvSignInn.isVisible = true
                        b.cconstrain.isVisible = true
                        b.loading.isVisible = false
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        b.tvForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Umitsań men ne qılayın!", Toast.LENGTH_SHORT).show()
        }

    }
}