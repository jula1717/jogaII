package com.example.jogaii.ui.asanas

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        fun onImgCompletedClick(asana: Asana)
    }
  inner class AsanaViewHolder(private val binding: ItemAsanaBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.apply {
                    imgComplete.setOnClickListener{
                        val position = adapterPosition
                        if(position!=RecyclerView.NO_POSITION){
                            val asanaWithType = getItem(position)
                            listener.onImgCompletedClick(asanaWithType.asana)
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
        fun bind(asana: AsanaWithType,holder:AsanaViewHolder) {
            binding.apply {
                txtAsanaSanskritName.text = asana.asana.sanskritName
                txtAsanaName.text = asana.asana.name
                imgAsana.setImageResource(asana.asana.imgRes)

                val resColor = if(asana.asana.completed) R.color.completed else R.color.uncompleted
                val colorValue = ContextCompat.getColor(holder.itemView.context, resColor)

                var colorFilter = PorterDuffColorFilter(colorValue,PorterDuff.Mode.SRC_ATOP)

                imgComplete.colorFilter=colorFilter
                imgAsana.colorFilter=colorFilter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsanaViewHolder {
        val binding = ItemAsanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsanaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsanaViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem,holder)
    }

    class DiffCallback : DiffUtil.ItemCallback<AsanaWithType>() {
        override fun areItemsTheSame(oldItem: AsanaWithType, newItem: AsanaWithType): Boolean =
            oldItem.asana.asanaId == newItem.asana.asanaId

        override fun areContentsTheSame(oldItem: AsanaWithType, newItem: AsanaWithType): Boolean =
            oldItem == newItem

    }


}