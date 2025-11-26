package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String = "",
    val summary: String = "",
    val content: String = "",
    val author: String = "",
    val date: String = "",
    val imageUrl: String = ""
) : Parcelable