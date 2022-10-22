package com.example.app.activities

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app.databinding.ActivitySongBinding
import com.example.app.model.SongModel
import java.io.File

class SongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongBinding
    private lateinit var song: SongModel
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaDataRetriever: MediaMetadataRetriever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        song = intent.getSerializableExtra("SONG") as SongModel
    }

    override fun onStart() {
        super.onStart()
        mediaDataRetriever = MediaMetadataRetriever()
        mediaDataRetriever.setDataSource(song.songPath)
        val album = mediaDataRetriever.embeddedPicture

        Log.d("ALBUM", album.toString() ?: "NULL")

        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(File(song.songPath)))

    }
}