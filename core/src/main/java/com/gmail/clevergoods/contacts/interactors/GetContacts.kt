package com.gmail.clevergoods.contacts.interactors

import com.gmail.clevergoods.contacts.data.ItemRepository

class GetContacts(private val itemRepository: ItemRepository) {

    suspend operator fun invoke() = itemRepository.getItems()
}