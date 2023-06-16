package com.speakuy.api

import com.google.gson.annotations.SerializedName

data class MentorResponse(

	@field:SerializedName("MentorResponse")
	val mentorResponse: List<MentorResponseItem?>? = null
)

data class MentorResponseItem(

	@field:SerializedName("earned")
	val earned: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avg_rate")
	val avgRate: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("similiarity")
	val similiarity: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("salary_per_hour")
	val salaryPerHour: Int? = null,

	@field:SerializedName("total_hours")
	val totalHours: Any? = null,

	@field:SerializedName("success_rate")
	val successRate: Any? = null,

	@field:SerializedName("total_job")
	val totalJob: Int? = null
)
