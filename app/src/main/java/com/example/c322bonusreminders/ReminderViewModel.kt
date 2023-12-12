package com.example.c322bonusreminders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReminderViewModel : ViewModel() {
    //variables
    private var auth: FirebaseAuth
    var user:User = User()
    var verifyPassword = ""
    var remindId:String = ""

    //reminders
    var reminder = MutableLiveData<Reminder>()
    private val _reminders: MutableLiveData<MutableList<Reminder>> = MutableLiveData()
    val reminders: LiveData<List<Reminder>>
        get() = _reminders as LiveData<List<Reminder>>

    //navigation for reminders
    private val _navigateToReminder = MutableLiveData<String?>()
    val navigateToReminder: LiveData<String?>
        get() = _navigateToReminder
    //error
    private val _errorHappened = MutableLiveData<String?>()
    val errorHappened: LiveData<String?>
        get() = _errorHappened

    //navigation for list
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    //navigation for sign up
    private val _navigateToSignUp = MutableLiveData<Boolean>(false)
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

    //navigation for sign in
    private val _navigateToSignIn = MutableLiveData<Boolean>(false)
    val navigateToSignIn: LiveData<Boolean>
        get() = _navigateToSignIn

    private lateinit var remindersCollection: DatabaseReference

    //initialize
    init {
        auth = Firebase.auth
        if (remindId.trim() == "")
            reminder.value = Reminder()
        _reminders.value = mutableListOf<Reminder>()
    }

    fun initializeTheDatabaseReference() {
        val database = Firebase.database
        remindersCollection = database
            .getReference("reminders")
            .child(auth.currentUser!!.uid)
        remindersCollection.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var reminderList: ArrayList<Reminder> = ArrayList()
                for (noteSnapshot in snapshot.children) {
                    var reminder = noteSnapshot.getValue<Reminder>()
                    reminder?.remindId = noteSnapshot.key!!
                    reminderList.add(reminder!!)
                }
                _reminders.value = reminderList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ReminderViewModel", error.toString())
            }
        })
    }
    /*
    @param none
    updates the current reminder on screen and database
     */
    fun updateReminder() {
        if (remindId.trim()=="") {
            remindersCollection.push().setValue(reminder.value)
        }else {
            remindersCollection.child(remindId).setValue(reminder.value)
        }
        _navigateToList.value = true
    }

    /*
    @param remindId:String to identify reminder
    deletes the reminder
     */
    fun deleteReminder(remindId: String) {
        remindersCollection.child(remindId).removeValue()
    }

    /*
    @param reminder:Reminder to identify reminder
    when a reminder is clicked, navigate to the selected reminder
     */
    fun onReminderClicked(selectedReminder:Reminder) {
        _navigateToReminder.value = selectedReminder.remindId
        remindId = selectedReminder.remindId
        reminder.value = selectedReminder
    }

    /*
    @param none
    creates new reminder
     */
    fun onNewReminderClicked() {
        _navigateToReminder.value = ""
        remindId = ""
        reminder.value = Reminder()
    }

    //navigation variables
    //reminder navigate
    fun onReminderNavigated() {
        _navigateToReminder.value = null
    }
    //list navigate
    fun onNavigatedToList() {
        _navigateToList.value = false
    }

    //signup navigate
    fun navigateToSignUp() {
        _navigateToSignUp.value = true
    }
    fun onNavigatedToSignUp() {
        _navigateToSignUp.value = false
    }
    //signin navigate
    fun navigateToSignIn() {
        _navigateToSignIn.value = true
    }
    fun onNavigatedToSignIn() {
        _navigateToSignIn.value = false
    }

    /*
    @param none
    signs in user by entering email and password (after completing sign up)
     */
    fun signIn() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            _errorHappened.value = "Email and password cannot be empty."
            return
        }
        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                initializeTheDatabaseReference()
                _navigateToList.value = true
            } else {
                _errorHappened.value = it.exception?.message
            }
        }
    }

    /*
    @param none
    sign up user by filling out email and password
     */
    fun signUp() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            _errorHappened.value = "Email and password cannot be empty."
            return
        }
        if (user.password != verifyPassword) {
            _errorHappened.value = "Password and verify do not match."
            return
        }
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                _navigateToSignIn.value = true
            } else {
                _errorHappened.value = it.exception?.message
            }
        }
    }

    /*
    @param none
    signs out current user
     */
    fun signOut() {
        auth.signOut()
        _navigateToSignIn.value = true
    }

    /*
    @param none
    return current user signed in
     */
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun feedback(){

    }
    fun about(){

    }
}