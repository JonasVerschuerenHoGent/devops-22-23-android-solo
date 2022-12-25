package com.example.test.screens.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.MemberListItemBinding
import com.example.test.domain.Member

class MemberListAdapter(val clickListener: MemberListener) : ListAdapter<Member, ViewHolder>(MemberDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

}

class ViewHolder(val binding : MemberListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: MemberListener, item : Member) {
        binding.textviewName.text = item.name
        binding.textviewRole.text = item.role.toString()
        binding.member = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent : ViewGroup) : ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MemberListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class MemberDiffCallback: DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        // Works perfectly because it's a dataclass
        return oldItem == newItem
    }
}

class MemberListener(val clickListener: (memberID: Int) -> Unit ) {
    fun onClick(member : Member) = clickListener(member.id)
}