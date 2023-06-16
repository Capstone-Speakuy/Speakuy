package com.speakuy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mentor(
    val name: String,
    val photo: String,
    val description: String,
) : Parcelable
