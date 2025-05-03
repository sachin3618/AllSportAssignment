package com.codesharkstudio.allsportsassignment.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesharkstudio.allsportsassignment.databinding.ItemSportBinding
import com.codesharkstudio.allsportsassignment.domain.model.Data

class SportAdapter(
    private val onDelete: (Int) -> Unit
) : ListAdapter<Data, SportAdapter.SportViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem.sport_id == newItem.sport_id
            override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem == newItem
        }
    }

    inner class SportViewHolder(private val binding: ItemSportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sport: Data) {
            binding.tvSportName.text = sport.sport_name
            binding.tvStatus.text = sport.status
            binding.btnDelete.setOnClickListener { onDelete(sport.sport_id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val binding = ItemSportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}