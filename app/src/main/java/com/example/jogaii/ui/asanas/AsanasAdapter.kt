package com.example.jogaii.ui.asanas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jogaii.R
import com.example.jogaii.data.Asana
import com.example.jogaii.databinding.ItemAsanaBinding


class AsanasAdapter : ListAdapter<Asana, AsanasAdapter.AsanaViewHolder>(DiffCallback()) {

    class AsanaViewHolder(private val binding: ItemAsanaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asana: Asana) {
            binding.apply {
                txtAsanaSanskritName.text = asana.sanskritName
                txtAsanaName.text = asana.name
                imgAsana.setImageResource(asana.imgRes)
                val color = if (asana.completed) R.color.s3 else R.color.light_gray
                doneIcon.setColorFilter(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsanaViewHolder {
        val binding = ItemAsanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsanaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsanaViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Asana>() {
        override fun areItemsTheSame(oldItem: Asana, newItem: Asana): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Asana, newItem: Asana): Boolean =
            oldItem == newItem

    }


}