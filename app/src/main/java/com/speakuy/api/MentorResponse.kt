package com.speakuy.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MentorResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("data")
	val listMentor: List<Mentor>? = null,
)

@Parcelize
data class Mentor(

	@field:SerializedName("earned")
	val earned: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avg_rate")
	val avgRate: Double? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("similiarity")
	val similiarity: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("salary_per_hour")
	val salaryPerHour: Int? = null,

	@field:SerializedName("total_hours")
	val totalHours: Double? = null,

	@field:SerializedName("success_rate")
	val successRate: Double? = null,

	@field:SerializedName("total_job")
	val totalJob: Int? = null
) : Parcelable
