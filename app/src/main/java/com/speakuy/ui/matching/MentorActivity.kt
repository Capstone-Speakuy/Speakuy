package com.speakuy.ui.matching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.speakuy.R
import com.speakuy.databinding.ActivityMentorBinding
import com.speakuy.model.Mentor

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Recommended Mentors"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        binding.rvMentorResult.layoutManager = LinearLayoutManager(this)
        setListItem(mentorDummy)
    }

    private fun setListItem(mentors: List<Mentor>) {
        val adapter = MentorAdapter(mentors)
        binding.rvMentorResult.adapter = adapter
    }

    val mentorDummy = listOf(
        Mentor("Wayan","https://source.unsplash.com/7RPqHmNDZaE","Bekasi"),
        Mentor("Dimas","https://source.unsplash.com/7RPqHmNDZaE","Yogyakarta"),
        Mentor("Imam","https://source.unsplash.com/7RPqHmNDZaE","Surabaya"),
        Mentor("Hifdzi","https://source.unsplash.com/7RPqHmNDZaE","Bandung"),
        Mentor("Arief","https://source.unsplash.com/7RPqHmNDZaE","Jakarta"),
        Mentor("Haidar","https://source.unsplash.com/7RPqHmNDZaE","Palembang"),
        Mentor("Ricky","https://source.unsplash.com/7RPqHmNDZaE","Medan"),
        Mentor("Atthabiq","https://source.unsplash.com/7RPqHmNDZaE","Semarang"),
        Mentor("Amir","https://source.unsplash.com/7RPqHmNDZaE","Batam"),
        Mentor("Airlangga","https://source.unsplash.com/7RPqHmNDZaE","Malang"),
    )
}