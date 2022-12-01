package com.example.tasksapplicationfinalversion.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.tasksapplicationfinalversion.R
import com.example.tasksapplicationfinalversion.data.TaskEntity
import com.example.tasksapplicationfinalversion.databinding.FragmentTaskBinding
import com.example.tasksapplicationfinalversion.viewmodel.TasksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTask : Fragment(R.layout.fragment_task) {

    private val viewModel by viewModel<TasksViewModel>()
    private lateinit var userList: MutableList<List<TaskEntity>>
    private lateinit var binding: FragmentTaskBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskBinding.bind(view)

        val taskAdapter = TaskRVAdapter()
        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }
        binding.apply {
            tvRecycler.apply {
                adapter = taskAdapter
                //es metodi aris recycleris optimizaciis da
                // viyenebt tu vicit rom recycleri dimenshenebs ar
                // icvlis shegvidzlia gamovtovot mara es cota ufro
                // metad efficients xdis recyclers
                setHasFixedSize(true)
            }
            btnAdd.setOnClickListener {
                lifecycleScope.launch {
                    addRecord()
                }
            }
        }

    }

    private suspend fun addRecord () {
        val task = binding.editText.text.toString()
        if (task.isNotEmpty()) {
            viewModel.insertNewTask(TaskEntity(taskName = task))
            binding.editText.text.clear()
        }else{
            Toast.makeText(
                activity,
                "taski cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}