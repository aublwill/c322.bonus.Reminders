package com.example.c322bonusreminders

import androidx.recyclerview.widget.DiffUtil

class RemindDiffItemCallback: DiffUtil.ItemCallback<Reminder>() {

    //determine if two items or contents are the same
    override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder)
            = (oldItem.remindId == newItem.remindId)

    override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder)
            = (oldItem == newItem)
}