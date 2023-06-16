package com.speakuy.ui.onboarding

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.speakuy.R
import com.speakuy.ui.MainActivity
import com.speakuy.ui.auth.AuthActivity
import com.speakuy.ui.auth.AuthViewModel
import com.speakuy.ui.auth.SettingPreferences
import com.speakuy.ui.auth.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

class SplashFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        authViewModel = ViewModelProvider(this, ViewModelFactory(pref))[AuthViewModel::class.java]

        Handler(Looper.getMainLooper()).postDelayed({
//            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)

//            if (onBoardingFinished()) {
//                findNavController().navigate(R.id.action_splashFragment_to_loginActivity)
//            } else {
//                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
//            }

            authViewModel.getTokenPref().observe(viewLifecycleOwner) {
                if (it != null) moveTo(MainActivity::class.java) else moveTo(AuthActivity::class.java)
                Log.d("testo", "splash: $it")
            }

            requireActivity().finish()
        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun moveTo(page: Class<*>) {
        val intent = Intent(requireActivity(), page)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("Finished", false)
    }
}