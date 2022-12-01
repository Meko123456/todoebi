package com.example.tasksapplicationfinalversion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.tasksapplicationfinalversion.data.TaskEntity
import com.example.tasksapplicationfinalversion.databinding.FragmentSeparateTaskBinding
import com.example.tasksapplicationfinalversion.viewmodel.TasksViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SeparateTaskFragment : Fragment(R.layout.fragment_separate_task) {

    private lateinit var binding: FragmentSeparateTaskBinding
    private val viewModel by viewModel<TasksViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSeparateTaskBinding.bind(view)

        val args: SeparateTaskFragmentArgs = SeparateTaskFragmentArgs.fromBundle(requireArguments())
        val specificId = args.id.toInt()

        binding.apply {
            getTaskById(specificId).observe(viewLifecycleOwner) {
                taskId.text = it.id.toString()
                taskName.text = it.taskName
                taskIsCompleted.text = it.isCompleted.toString()
            }
        }


        binding.btnGoBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_separateTaskFragment_to_taskFragment)
        }
    }

    private fun getTaskById(id: Int): LiveData<TaskEntity> {
        return viewModel.getTaskById(id)
    }

}