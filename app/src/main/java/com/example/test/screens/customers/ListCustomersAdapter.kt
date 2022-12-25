package com.example.test.screens.customers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.CustomerListItemBinding
import com.example.test.domain.Customer

class ListCustomersAdapter(val clickListener: CustomerListener) : ListAdapter<Customer, ViewHolder>(CustomerDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

}

class ViewHolder(val binding : CustomerListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: CustomerListener, item : Customer) {
        binding.textviewName.text = item.name
        //binding.textviewDepartment.text = item.department.toString()
        binding.customer = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent : ViewGroup) : ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CustomerListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class CustomerDiffCallback: DiffUtil.ItemCallback<Customer>() {
    override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        // Works perfectly because it's a dataclass
        return oldItem == newItem
    }
}

class CustomerListener(val clickListener: (customerId: Int) -> Unit ) {
    fun onClick(customer : Customer) = clickListener(customer.id)
}