package com.example.jogaii.ui.asanas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jogaii.R
import com.example.jogaii.data.Asana
import com.example.jogaii.data.AsanaWithType
import com.example.jogaii.databinding.ItemAsanaBinding


class AsanasAdapter (private val listener: OnItemClickListener): ListAdapter<AsanaWithType, AsanasAdapter.AsanaViewHolder>(DiffCallback()) {

    interface OnItemClickListener{
        fun onItemClick(asana:AsanaWithType)
        fun onDoneIconClick(asana: Asana, imgComplete:ImageView, imgAsana:ImageView)
    }
  inner class AsanaViewHolder(private val binding: ItemAsanaBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.apply {
                    doneIcon.setOnClickListener{
                        val position = adapterPosition
                        if(position!=RecyclerView.NO_POSITION){
                            val asanaWithType = getItem(position)
                            listener.onDoneIconClick(asanaWithType.asana,doneIcon,imgAsana)
                        }
                    }
                    root.setOnClickListener{
                        val position = adapterPosition
                        if(position!=RecyclerView.NO_POSITION){
                            val asana = getItem(position)
                            listener.onItemClick(asana)
                        }
                    }
                }
            }
        fun bind(asana: AsanaWithType) {
            binding.apply {
                txtAsanaSanskritName.text = asana.asana.sanskritName
                txtAsanaName.text = asana.asana.name
                imgAsana.setImageResource(asana.asana.imgRes)
                val color = if (asana.asana.completed) R.color.s3 else R.color.light_gray
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

    class DiffCallback : DiffUtil.ItemCallback<AsanaWithType>() {
        override fun areItemsTheSame(oldItem: AsanaWithType, newItem: AsanaWithType): Boolean =
            oldItem.asana.asanaId == newItem.asana.asanaId

        override fun areContentsTheSame(oldItem: AsanaWithType, newItem: AsanaWithType): Boolean =
            oldItem == newItem

    }


}