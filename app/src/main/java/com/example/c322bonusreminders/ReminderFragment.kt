package com.example.c322bonusreminders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.c322bonusreminders.databinding.FragmentRemindersBinding

class ReminderFragment : Fragment(){
    val TAG = "ReminderFragment"
    //binding/viewmodel variables
    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

    val viewModel : ReminderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize reminders if logged in
        viewModel.initializeTheDatabaseReference()
        //variables
        _binding = FragmentRemindersBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //access variable to access clicked reminder
        fun reminderClicked(reminder:Reminder){
            viewModel.onReminderClicked(reminder)
        }
        //delete reminder if "yes" is pressed from dialog
        fun yesPressed(remindId:String){
            Log.d(TAG, "in yesPressed(): remindId = $remindId")
            binding.viewModel?.deleteReminder(remindId)
        }
        //show dialog when delete button is clicked
        fun deleteClicked(remindId:String){
            ConfirmDeleteDialogFragment(remindId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }

        //adapter
        val adapter = ReminderItemAdapter(::reminderClicked, ::deleteClicked)
        binding.reminderList.adapter = adapter

        //observe reminders
        viewModel.reminders.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        //navigate to clicked reminder
        viewModel.navigateToReminder.observe(viewLifecycleOwner, Observer { remindId->
            remindId?.let{
                val action = ReminderFragmentDirections
                    .actionRemindersFragmentToEditReminderFragment(remindId)
                this.findNavController().navigate(action)
                viewModel.onReminderNavigated()
            }
        })
        // navigate to sign in page
        viewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer {navigate->
            if (navigate){
                this.findNavController().navigate(R.id.action_reminderFragment_to_signInFragment)
                viewModel.onNavigatedToSignIn()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}