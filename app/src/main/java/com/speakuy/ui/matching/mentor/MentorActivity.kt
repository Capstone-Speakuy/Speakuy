package com.speakuy.ui.matching.mentor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.speakuy.R
import com.speakuy.api.Mentor
import com.speakuy.databinding.ActivityMentorBinding
import com.speakuy.ui.auth.AuthViewModel
import com.speakuy.ui.auth.SettingPreferences
import com.speakuy.ui.auth.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding
    private lateinit var mentorViewModel: MentorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Recommended Mentors"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        val pref = SettingPreferences.getInstance(dataStore)
        mentorViewModel = ViewModelProvider(this, ViewModelFactory(pref))[MentorViewModel::class.java]
        mentorViewModel.getMentor()
        mentorViewModel.mentorResponse.observe(this) {
            if (it.listMentor!!.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                setListItem(it.listMentor)
            }
            Log.d("testo", "onCreate: $it")
        }
        mentorViewModel.message.observe(this) {
            binding.tvEmpty.text = it
            Toast.makeText(this, "Error $it, try again", Toast.LENGTH_LONG).show()
        }
        mentorViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.rvMentorResult.layoutManager = LinearLayoutManager(this)
    }

    private fun setListItem(mentors: List<Mentor>) {
        val adapter = MentorAdapter(mentors)
        binding.rvMentorResult.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}