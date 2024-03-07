package com.bionichamza.quabionicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import com.bionichamza.quabionicapp.models.ProstheticsInfoResult
import com.bionichamza.quabionicapp.models.ProstheticsInfoS
import com.example.quabionicapp.databinding.HomeProsRowLayoutBinding
import com.bionichamza.quabionicapp.util.ProstheticsDiffUtil

class HomeProsAdapter : RecyclerView.Adapter<HomeProsAdapter.HomeProsViewHolder>() {

    private var prosthetics = emptyList<ProstheticsInfo>()

    class HomeProsViewHolder(private val binding: HomeProsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(prostheticsInfo: ProstheticsInfo) {
            binding.result = prostheticsInfo
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent : ViewGroup) : HomeProsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeProsRowLayoutBinding.inflate(layoutInflater , parent, false)
                return HomeProsViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProsViewHolder {
        return HomeProsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeProsViewHolder, position: Int) {
        val currentProsthetics = prosthetics[position]
        holder.bind(currentProsthetics)
    }

    override fun getItemCount(): Int {
        return prosthetics.size
    }

    fun setData(newData: ProstheticsInfo) {
        val prostheticsDiffUtil = ProstheticsDiffUtil(prosthetics, newData.prostheticsInfoResults)
        val diffUtilResult = DiffUtil.calculateDiff(prostheticsDiffUtil)
        //prosthetics = newData.prostheticsInfoResults
        diffUtilResult.dispatchUpdatesTo(this)
    }
}