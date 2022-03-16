package com.gmail.clevergoods.contact.presentation.contactsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.gmail.clevergoods.contact.databinding.FragmentFirstBinding

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.clevergoods.contact.R
import com.gmail.clevergoods.contact.framework.ContactsViewModelFactory
import com.gmail.clevergoods.contacts.domain.Contact
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISelectionListener
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.getSelectExtension


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ContactsListFragment : Fragment() {

    private lateinit var viewModel: ContactsListViewModel
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        val itemAdapter = ItemAdapter<ContactItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        recyclerView.adapter = fastAdapter
        viewModel = ViewModelProviders.of(this, ContactsViewModelFactory)
            .get(ContactsListViewModel::class.java)
        viewModel.items.observe(this, Observer {
            val contactItems = it.map(::ContactItem)
            for (i in contactItems.indices) {
                contactItems[i].intCheckBox(viewModel, i)
            }
            FastAdapterDiffUtil[itemAdapter] = contactItems
        })

        viewModel.selectedItems.observe(this, Observer {
        })
        viewModel.loadItems()

        fastAdapter.onClickListener = { _view, _adapter, _item, _position ->
            val tvBox = _view?.findViewById<CheckBox>(R.id.tvBox)
            if (tvBox != null) {
                val isChecked = !tvBox.isChecked
                tvBox.isChecked = isChecked
                viewModel.setSelectedItem(_position)
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}