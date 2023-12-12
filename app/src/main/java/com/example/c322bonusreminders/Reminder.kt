package com.example.c322bonusreminders

import com.google.firebase.database.Exclude

//data for Note object
//  has an id,name,and description/content
data class Reminder(
    @get:Exclude
    var remindId:String = "",
    var remindName: String = "",
    var remindDescription:String ="",
    var remindDate: String = "",
    var remindTime:String = ""

)