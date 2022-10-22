package com.example.app.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.adapter.MusicRecyclerViewAdapter
import com.example.app.databinding.ActivityMusicHomeBinding
import com.example.app.model.SongModel

class MusicHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicHomeBinding
    private lateinit var musicAdapter: MusicRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
        val songs = findMp3Files()
        initRecyclerView(songs)
    }

    private fun initRecyclerView(songs: List<SongModel>) {
        musicAdapter = MusicRecyclerViewAdapter(songs)
        binding.apply {
            musicRecyclerView.layoutManager = LinearLayoutManager(this@MusicHomeActivity)
            musicRecyclerView.adapter = musicAdapter
        }
    }

    private fun findMp3Files() =
        Environment.getExternalStorageDirectory().walkTopDown().filter { file ->
            file.name.endsWith(".mp3")
        }.toList().map {
            SongModel(songName = it.name, songPath = it.absolutePath)
        }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions()
        }
    }

    private fun requestPermissions() {

    }

}