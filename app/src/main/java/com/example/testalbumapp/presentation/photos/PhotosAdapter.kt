package com.example.testalbumapp.presentation.photos

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testalbumapp.R
import com.example.testalbumapp.domain.entity.Photo
import com.example.testalbumapp.utils.diffItemCallback
import com.example.testalbumapp.utils.inflate
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private val click: (Photo) -> Unit
) :
    androidx.recyclerview.widget.ListAdapter<Photo, PhotosAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_photo_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        fun bind(photo: Photo?) {
            Picasso.get().load(photo?.thumbnailUrl).into(image)
            itemView.setOnClickListener {
                click(getItem(adapterPosition) ?: return@setOnClickListener)
            }
        }
    }
}