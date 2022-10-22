package com.example.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.activities.SongActivity
import com.example.app.databinding.MusicItemBinding
import com.example.app.model.SongModel
import java.io.File

class MusicRecyclerViewAdapter constructor(val songList: List<SongModel>) : RecyclerView.Adapter<MusicRecyclerViewAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(songList[position], position + 1, itemCount)
    }

    override fun getItemCount() = songList.size

    class MusicViewHolder(private val binding: MusicItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: SongModel, index: Int, listSize: Int) {
            reset()
            binding.songName.text = song.songName
            binding.songNumber.text = index.toString()
            binding.listItem.setOnClickListener {
                val intent = Intent(it.context, SongActivity::class.java)
                intent.putExtra("SONG", song)
                it.context.startActivity(intent)
            }
        }

        private fun reset() {
            binding.songName.text = ""
            binding.songNumber.text = ""
        }


    }
}