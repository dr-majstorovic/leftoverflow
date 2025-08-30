package com.example.leftoverflow.data.model

data class Recipe(
    val title: String,
    val time: String,
    val type: String,
    val imageRes: Int // Drawable res ID (kasnije može URL ako koristiš Glide/Picasso)
)
