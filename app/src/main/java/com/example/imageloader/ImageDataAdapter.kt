package com.example.imageloader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageDataAdapter(private val onClick: (ImageDataView) -> Unit) :
    ListAdapter<ImageDataView, ImageDataAdapter.ImageDataViewHolder>(FlowerDiffCallback) {


    class ImageDataViewHolder(itemView: View, val onClick: (ImageDataView) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tvIsModified: TextView = itemView.findViewById(R.id.tv_isModified_title)
        private val tvIsDeleted: TextView = itemView.findViewById(R.id.tv_isDeleted)
        private val tvIsNews: TextView = itemView.findViewById(R.id.tv_isNew)
        private val imageView: ImageView = itemView.findViewById(R.id.iv_image)
        private var imageData: ImageDataView? = null

        init {
            itemView.setOnClickListener {
                imageData?.let {
                    onClick(it)
                }
            }
        }

        fun bind(data: ImageDataView) {
            imageData = data
            if (data.isModified) {
                tvIsModified.text = imageView.context.resources.getString(R.string.label_isModified)
            } else {
                tvIsModified.visibility = View.GONE
            }
            tvIsDeleted.text = data.isSoftDeleted.toString()
            tvIsNews.text = data.isNewItem.toString()
            Glide.with(tvIsModified.context).load(data.path).into(imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_data_item, parent, false)
        return ImageDataViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ImageDataViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}


object FlowerDiffCallback : DiffUtil.ItemCallback<ImageDataView>() {
    override fun areItemsTheSame(oldItem: ImageDataView, newItem: ImageDataView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageDataView, newItem: ImageDataView): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}