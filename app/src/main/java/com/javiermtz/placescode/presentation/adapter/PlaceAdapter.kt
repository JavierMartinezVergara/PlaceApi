package com.javiermtz.placescode.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javiermtz.placescode.databinding.PlaceItemBinding
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.presentation.adapter.PlaceAdapter.ViewHolder

class PlaceAdapter(
  private val onClickListener: OnClickListener,
) :
  ListAdapter<Place, ViewHolder>(MyDiffUtil) {

  companion object MyDiffUtil : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
      return oldItem.name == newItem.name
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      PlaceItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val result = getItem(position)
    holder.itemView.setOnClickListener {
      onClickListener.onClick(result)
    }
    holder.bind(result)
  }

  inner class ViewHolder(private val binding: PlaceItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(place: Place?) {
      binding.namePlace.text = place?.name
      binding.descriptionPlace.text = place?.lat.toString()
      binding.ratingPlace.text = place?.rating.toString()

    }
  }

  class OnClickListener(val clickListener: (result: Place) -> Unit) {
    fun onClick(result: Place) = clickListener(result)
  }

}




