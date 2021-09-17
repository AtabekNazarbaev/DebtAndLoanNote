package uz.texnopos.qarzdpter.ui.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.qarzdpter.R
import uz.texnopos.qarzdpter.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpBinding.bind(view)

        btnSignUp.setOnClickListener {
            when {
                binding.etEmail.text.toString().isEmpty() -> {
                    binding.etEmail.error = "Введите email"
                }
                binding.etPassword.text.toString().isEmpty() -> {
                    binding.etPassword.error = "Введите пароль"
                }
                else -> {
                    viewModel.signUp(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    )
                }
            }
        }
        viewModel.signUp.observe(viewLifecycleOwner, {
            when (it) {
                "loading" -> {
                    Toast.makeText(requireContext(), "Please, wait", Toast.LENGTH_SHORT).show()
                }
                "success" -> {
                    findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                }
                else -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.tvGoToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Umitsań men ne qılayın!", Toast.LENGTH_SHORT).show()
        }
    }
}