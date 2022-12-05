package com.example.test.screens.users

import android.os.Binder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.UserListItemBinding
import com.example.test.domain.Account

class ListUsersAdapter(val clickListener: AccountClickListener) : ListAdapter<Account, ViewHolder>(AccountDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

}

class ViewHolder(val binding : UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(clickListener: AccountClickListener, item : Account) {
        binding.textView.text = item.name
        binding.account = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent : ViewGroup) : ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class AccountDiffCallback: DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        // Works perfectly because it's a dataclass
        return oldItem == newItem
    }
}


class AccountClickListener(val clickListener: (accountID: Int) -> Unit ) {
    fun onclick(account : Account) = clickListener(account.id);

}