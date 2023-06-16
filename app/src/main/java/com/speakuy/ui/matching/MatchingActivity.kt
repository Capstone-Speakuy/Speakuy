package com.speakuy.ui.matching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import com.speakuy.databinding.ActivityMatchingBinding
import com.speakuy.ui.MainActivity
import com.speakuy.ui.matching.mentor.MentorActivity

class MatchingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchingBinding
    private var data: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMatchingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        data = intent.getStringExtra("q1")

        binding.imgTest.setOnClickListener {
            moveTo(TestActivity::class.java)
        }
        binding.imgMentor.setOnClickListener {
            if (data != null) {
                moveTo(MentorActivity::class.java)
            } else {
                Toast.makeText(this, "Take a test first", Toast.LENGTH_SHORT).show()
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            if (data != null) confirm() else finish()
        }
    }

    private fun moveTo(page: Class<*>) {
        val intent = Intent(this, page)
        startActivity(intent)
    }

    private fun confirm() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Previous data will be lost")
            setMessage("Are you sure?")
            setPositiveButton("Yes") { _, _ ->
                moveTo(MainActivity::class.java)
                finish() }
            setNegativeButton("No") { dialog, _ -> dialog.dismiss()}
        }
        val dialog = builder.create()
        dialog.show()
    }

}