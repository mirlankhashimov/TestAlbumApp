package com.example.testalbumapp.ui.album

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testalbumapp.R
import com.example.testalbumapp.utils.diffItemCallback
import com.example.testalbumapp.utils.inflate
import com.example.testalbumapp.domain.model.Album

class AlbumAdapter(
    private val click: (Album) -> Unit
) :
    PagedListAdapter<Album, AlbumAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_album_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView = itemView.findViewById(R.id.title)
        fun bind(album: Album?) {
            name.text = album?.title
            itemView.setOnClickListener {
                click(getItem(adapterPosition) ?: return@setOnClickListener)
            }
        }
    }
}