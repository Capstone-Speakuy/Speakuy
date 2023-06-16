package com.speakuy.ui.message

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.speakuy.R
import com.speakuy.api.Mentor
import com.speakuy.ui.matching.DetailActivity

class MessageAdapter(private val listMentor: List<Mentor>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgMentor: ImageView = view.findViewById(R.id.img_avatar_mentor)
        private val nameMentor: TextView = view.findViewById(R.id.tv_name_mentor)
        private val descMentor: TextView = view.findViewById(R.id.tv_desc_mentor)

        fun bind(mentor: Mentor) {
//            Glide.with(itemView.context)
//                .load(mentor.photo)
//                .circleCrop()
//                .into(imgMentor)
            nameMentor.text = mentor.name
            descMentor.text = mentor.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("mentorData", mentor)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mentor_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMentor[position])
    }

    override fun getItemCount() = listMentor.size
}