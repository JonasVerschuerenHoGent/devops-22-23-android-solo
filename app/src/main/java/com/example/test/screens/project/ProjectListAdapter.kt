package com.example.test.screens.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ProjectFragmentItemBinding
import com.example.test.databinding.ProjectFragmentItemListBinding
import com.example.test.domain.Project

class ProjectListAdapter(val clickListener: ProjectListener) : ListAdapter<Project, ProjectViewHolder>(ProjectDiffCallback()) {

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder.from(parent)
    }

}

class ProjectViewHolder(val binding : ProjectFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: ProjectListener, item : Project) {

        binding.projectName.text = item.name
        binding.virtualMachines.text = item.virtualMachines.size.toString()
        binding.projectItem = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent : ViewGroup) : ProjectViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ProjectFragmentItemBinding.inflate(layoutInflater, parent, false)
            return ProjectViewHolder(binding)
        }
    }
}

class ProjectDiffCallback: DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        // Works perfectly because it's a dataclass
        return oldItem == newItem
    }
}

class ProjectListener(val clickListener: (projectName: String) -> Unit ) {
    fun onClick(project : Project) = clickListener(project.name)
}