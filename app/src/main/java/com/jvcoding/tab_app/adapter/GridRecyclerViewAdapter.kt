package com.jvcoding.tab_app.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jvcoding.tab_app.R

class GridRecyclerViewAdapter(
    private var imagesUriList: List<Uri>,
    private val onPhotoDeleteListener: OnPhotoDeleteClickListener
): RecyclerView.Adapter<GridRecyclerViewAdapter.GridRecyclerViewHolder>() {

    inner class GridRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemPhoto: ImageView = itemView.findViewById(R.id.imageView_itemRecyclerView_imageThumb)
        val closeIcon: ImageView = itemView.findViewById(R.id.imageView_itemRecyclerView_closeIcon)

        fun onBindingImage(imageUri: Uri) {
            itemPhoto.setImageURI(imageUri)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newUriList: List<Uri>) {
        imagesUriList= newUriList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridRecyclerViewHolder {
        return GridRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return imagesUriList.size
    }

    override fun onBindViewHolder(holder: GridRecyclerViewHolder, position: Int) {
        holder.onBindingImage(imagesUriList[position])

        holder.closeIcon.setOnClickListener {
            try {
                onPhotoDeleteListener.onPhotoDelete(imagesUriList[position])
            } catch (t:Throwable) {
                println(t.message.toString())
            }
        }
    }

}

interface OnPhotoDeleteClickListener {
    fun onPhotoDelete(uri: Uri)
}
