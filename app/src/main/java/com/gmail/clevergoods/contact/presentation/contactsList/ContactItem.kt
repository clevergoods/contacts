package com.gmail.clevergoods.contact.presentation.contactsList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.clevergoods.contact.R
import com.gmail.clevergoods.contact.databinding.FragmentItemBinding
import com.gmail.clevergoods.contacts.domain.Contact
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import kotlin.properties.Delegates

class ContactItem(val contact: Contact) : AbstractBindingItem<FragmentItemBinding>() {

    override val type: Int
        get() = R.id.contact_row_layout

    private lateinit var viewModel: ContactsListViewModel
    private var position by Delegates.notNull<Int>()

    fun intCheckBox(viewModel: ContactsListViewModel, position: Int) {
        this.viewModel = viewModel
        this.position = position
    }

    override fun bindView(binding: FragmentItemBinding, payloads: List<Any>) {
        binding.tvFio.text = contact.fio
        binding.tvPhone.text = contact.phone
        binding.tvBox.isChecked = contact.isSelected
        val tvBox = binding.tvBox
        tvBox.setOnClickListener() {
            viewModel.setSelectedItem(position)
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FragmentItemBinding {
        return FragmentItemBinding.inflate(inflater, parent, false)
    }
}