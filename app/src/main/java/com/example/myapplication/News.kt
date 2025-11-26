package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: String? = null,
    val title: String? = null,
    val summary: String? = null,
    val content: String? = null,
    val author: String? = null,
    val date: String? = null
) : Parcelable
