package com.speakuy.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.speakuy.databinding.FragmentSettingBinding
import com.speakuy.ui.auth.AuthActivity
import com.speakuy.ui.auth.AuthViewModel
import com.speakuy.ui.auth.SettingPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingViewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        settingViewModel = ViewModelProvider(this, ViewModelFactory(pref))[SettingViewModel::class.java]

        binding.btnLogout.setOnClickListener { confirmLogout() }
    }

    private fun logout() {
        val intent = Intent(requireActivity(), AuthActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
        settingViewModel.deleteTokenPref()
        toast()
    }

    private fun toast() {
        Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
    }

    private fun confirmLogout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("Logout")
            setMessage("Are you sure you want to logout?")
            setPositiveButton("Yes") { _, _ -> logout() }
            setNegativeButton("No", null)
        }
        val dialog = builder.create()
        dialog.show()
    }
}