package com.bionichamza.quabionicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bionichamza.quabionicapp.models.ProstheticsInfoItem
import com.example.quabionicapp.databinding.HomeProsRowLayoutBinding
import com.example.quabionicapp.R

class HomeProsAdapter : RecyclerView.Adapter<HomeProsAdapter.HomeProsViewHolder>() {

    private var prosthetics = ArrayList<ProstheticsInfoItem>()

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

    fun prostheticsListUpdate(newProsthetics : List<ProstheticsInfoItem>) {
        prosthetics.clear()
        prosthetics.addAll(newProsthetics)
        notifyDataSetChanged()
    }

    /*
    fun setData(newData: ProstheticsInfo) {
        val prostheticsInfoDiffUtil = ProstheticsDiffUtil(prosthetics, newData)
        val diffUtilResult = DiffUtil.calculateDiff(prostheticsInfoDiffUtil)
        prosthetics = (newData)
        diffUtilResult.dispatchUpdatesTo(this)
    }

     */
}