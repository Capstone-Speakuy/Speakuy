package com.speakuy.ui.message

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.speakuy.api.Mentor
import com.speakuy.databinding.FragmentMessageBinding
import com.speakuy.ui.auth.SettingPreferences
import com.speakuy.ui.auth.ViewModelFactory
import com.speakuy.ui.matching.mentor.MentorAdapter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!
    private lateinit var messageViewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val pref = SettingPreferences.getInstance(requireContext().dataStore)

        messageViewModel = ViewModelProvider(this, ViewModelFactory(pref))[MessageViewModel::class.java]
        messageViewModel.getMyMentor()
        messageViewModel.mentorResponse.observe(viewLifecycleOwner) {
            if (it.listMentor!!.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                setListItem(it.listMentor)
            }
        }
        messageViewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error $it, try again", Toast.LENGTH_LONG).show()
        }
        messageViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.rvMessage.layoutManager = LinearLayoutManager(requireActivity())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListItem(mentors: List<Mentor>) {
        val adapter = MentorAdapter(mentors)
        binding.rvMessage.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}