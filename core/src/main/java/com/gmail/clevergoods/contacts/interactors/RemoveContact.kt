package com.gmail.clevergoods.contacts.interactors

import com.gmail.clevergoods.contacts.data.ItemRepository
import com.gmail.clevergoods.contacts.domain.Contact

class RemoveContact(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(contact: Contact){
            itemRepository.removeItem(contact)
    }
}