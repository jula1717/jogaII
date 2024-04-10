package com.example.jogaii.ui.progress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jogaii.data.AsanaTypeProgress
import com.example.jogaii.databinding.ItemProgressBinding

class ProgressAdapter : ListAdapter<AsanaTypeProgress, ProgressAdapter.ProgressViewHolder>(ProgressAdapter.DiffCallback()) {

    inner class ProgressViewHolder(private val binding: ItemProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(type: AsanaTypeProgress) {
            binding.apply {
                txtAsanaType.text = type.type.typeName
                imgAsanaType.setImageResource(type.type.typeImgRes)
                txtCompleted.text = type.completedAsanas.toString()
                txtTotalAsanas.text = type.totalAsanas.toString()
                pbAsanaType.setMax(type.totalAsanas*1000)
                pbAsanaType.setProgress(type.completedAsanas*1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val binding = ItemProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<AsanaTypeProgress>() {
        override fun areItemsTheSame(oldItem: AsanaTypeProgress, newItem: AsanaTypeProgress): Boolean =
            oldItem.type.typeId==newItem.type.typeId

        override fun areContentsTheSame(oldItem: AsanaTypeProgress, newItem: AsanaTypeProgress): Boolean =
            oldItem == newItem

    }

}