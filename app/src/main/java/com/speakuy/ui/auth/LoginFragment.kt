package com.speakuy.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.speakuy.R
import com.speakuy.databinding.FragmentLoginBinding
import com.speakuy.ui.MainActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var edEmail: EditText
    private lateinit var edPass: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edEmail = binding.edEmailLogin
        edPass = binding.edPassLogin
        binding.txtRegister.setOnClickListener { toReg() }
        binding.btnLogin.setOnClickListener {
            authViewModel.login(edEmail.text.toString(), edPass.text.toString())
            authViewModel.loginResponse.observe(viewLifecycleOwner) {
                toast(it.message)
                if (it.message == "success") toMain()
            }
        }

        edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.edEmail.error = "tes"
            }

        })
    }

    private fun toMain() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun toReg() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentRegister = RegisterFragment()
        fragmentTransaction.replace(R.id.fragment_container, fragmentRegister)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}