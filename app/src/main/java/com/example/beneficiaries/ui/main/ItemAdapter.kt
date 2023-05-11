package com.example.beneficiaries.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.R
import com.example.beneficiaries.data.model.Beneficiary

class ItemAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<Beneficiary, ItemAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder(view: View, private val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val textFirstName: TextView = view.findViewById(R.id.textFirstName)
        private val textLastName: TextView = view.findViewById(R.id.textLastName)
        private val textType: TextView = view.findViewById(R.id.textType)
        private val textDesignation: TextView = view.findViewById(R.id.textDesignation)

        fun bind(item: Beneficiary) {
            textFirstName.text = item.firstName
            textLastName.text = item.lastName
            textType.text = item.beneType
            textDesignation.text = item.designation
            itemView.setOnClickListener {
                onClick(item.socialSecurityNumber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilCallback : ItemCallback<Beneficiary>() {
    override fun areItemsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
        return oldItem.socialSecurityNumber == newItem.socialSecurityNumber
    }

    override fun areContentsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
        return oldItem == newItem
    }
}
