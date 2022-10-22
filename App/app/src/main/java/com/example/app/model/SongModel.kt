package com.example.app.model

import java.io.Serializable

data class SongModel(
    val songName: String,
    val songPath: String
): Serializable
