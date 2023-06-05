package com.speakuy.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.speakuy.R
import com.speakuy.api.User
import com.speakuy.databinding.FragmentRegisterBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPass: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        authViewModel = ViewModelProvider(this, ViewModelFactory(pref))[AuthViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edName = binding.edNameReg
        edEmail = binding.edEmailReg
        edPass = binding.edPassReg
        binding.tvLogin.setOnClickListener { toLogin() }
        binding.btnRegister.setOnClickListener {
            authViewModel.registerx(User(edName.text.toString(), edEmail.text.toString(), edPass.text.toString()))
            authViewModel.response.observe(viewLifecycleOwner) {
                if (it.code == 200) toLogin()
                toast(it.status)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun toLogin() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentLogin = LoginFragment()
        fragmentTransaction.apply {
            replace(R.id.fragment_container, fragmentLogin)
            addToBackStack(null)
            commit()
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}