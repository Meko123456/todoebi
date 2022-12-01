package com.example.tasksapplicationfinalversion.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksapplicationfinalversion.data.TaskEntity
import com.example.tasksapplicationfinalversion.databinding.RecyclerItemVeiwBinding

class TaskRVAdapter :
// aq chveulebrivi adapterisgan gansxvavebit viyenebt listadapters,
// imitoro es itvlis gansxvavebas dzvel da axal lists shoris da
// amas aketebs background thredze
    ListAdapter<TaskEntity, TaskRVAdapter.TasksViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = RecyclerItemVeiwBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

    }

    class TasksViewHolder(private val binding: RecyclerItemVeiwBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskEntity) {
            binding.apply {
                tvTaskName.text = task.taskName
                tvTaskName.setOnClickListener{
                    val targetID = task.id.toString()
                    val action = FragmentTaskDirections
                        .actionTaskFragmentToSeparateTaskFragment(id = targetID)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }

        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<TaskEntity>() {

        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem == newItem

    }
}