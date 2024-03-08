package com.bionichamza.quabionicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import com.example.quabionicapp.databinding.HomeProsRowLayoutBinding
import com.bionichamza.quabionicapp.util.ProstheticsDiffUtil
import com.example.quabionicapp.R

class HomeProsAdapter : RecyclerView.Adapter<HomeProsAdapter.HomeProsViewHolder>() {

    private var prosthetics = emptyList<ProstheticsInfo>()

    class HomeProsViewHolder(var view: HomeProsRowLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {

        companion object {
            fun from(parent : ViewGroup) : HomeProsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = DataBindingUtil.inflate<HomeProsRowLayoutBinding>(layoutInflater , R.layout.home_pros_row_layout , parent ,false)
                return HomeProsViewHolder(view)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProsViewHolder {
        return HomeProsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeProsViewHolder, position: Int) {
        holder.view.prostheticsInfos = prosthetics[position]
    }

    override fun getItemCount(): Int {
        return prosthetics.size
    }

    fun setData(newData: ProstheticsInfo) {
        val prostheticsInfoDiffUtil = ProstheticsDiffUtil(prosthetics, newData)
        val diffUtilResult = DiffUtil.calculateDiff(prostheticsInfoDiffUtil)
        prosthetics = listOf(newData)
        diffUtilResult.dispatchUpdatesTo(this)
    }
}