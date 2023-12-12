package com.example.c322bonusreminders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.c322bonusreminders.databinding.FragmentEditReminderBinding

class EditReminderFragment : Fragment(){
    //binding variables
    private var _binding: FragmentEditReminderBinding? = null
    private val binding get() = _binding!!

    val viewModel : ReminderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //access/assign variables
        _binding = FragmentEditReminderBinding.inflate(inflater, container, false)
        val view = binding.root
        val remindId = EditReminderFragmentArgs.fromBundle(requireArguments()).remindId

        //val viewModel : RemindViewModel by activityViewModels()
        viewModel.remindId = remindId

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //navigate back to ReminderFragment (home screen with reminder)
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate ->
            if (navigate){
                view.findNavController()
                    .navigate(R.id.action_editReminderFragment_to_RemindersFragment)
                viewModel.onNavigatedToList()
            }
        })
        /*
        @param remindId:String to identify which reminder to use
        what to do if 'yes' is pressed during confirmation:
            delete the reminder that it is on
            navigate back to home screen
         */
        fun yesPressed(remindId:String){
            viewModel.deleteReminder(remindId)
            view.findNavController()
                .navigate(R.id.action_editReminderFragment_to_RemindersFragment)
        }
        /*
        @param remindId:String to identify reminder
        shows the dialog on whether to delete the reminder or not (extra confirmation)
         */
        fun deleteClicked(remindId:String){
            ConfirmDeleteDialogFragment(remindId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }
        //if delete button is pressed, access delete method
//        binding.bDelete.setOnClickListener {
//            deleteClicked(remindId)
//        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}