package com.example.c322bonusreminders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.c322bonusreminders.databinding.ReminderItemBinding

class ReminderItemAdapter (val clickListener : (reminder:Reminder) -> Unit,
                       val deleteClickListener: (remindId:String) -> Unit)
    :ListAdapter<Reminder, ReminderItemAdapter.RemindItemViewHolder>(RemindDiffItemCallback()){

    //inflate layout, bind data
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int)
            :RemindItemViewHolder = RemindItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder:RemindItemViewHolder, position:Int){
        val item = getItem(position)
        holder.bind(item, clickListener, deleteClickListener)
    }


    //bind data to items in RecyclerView
    class RemindItemViewHolder(val binding: ReminderItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        companion object {
            fun inflateFrom(parent: ViewGroup): RemindItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReminderItemBinding.inflate(layoutInflater, parent, false)
                return RemindItemViewHolder(binding)
            }
        }
        fun bind(item:Reminder, clickListener: (reminder:Reminder) -> Unit,
                 deleteClickListener: (remindId: String) -> Unit){
            binding.reminder = item
            binding.root.setOnClickListener{clickListener(item)}
        }
    }
}