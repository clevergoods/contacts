package com.gmail.clevergoods.contacts.data

import com.gmail.clevergoods.contacts.domain.Contact

class ItemRepository (
    private val itemDataSource: ItemDataSource) {

        suspend fun addItem(contact: Contact) = itemDataSource.add(contact)

        suspend fun getItems():List<Contact> = itemDataSource.readAll()

        suspend fun removeItem(contact: Contact) = itemDataSource.remove(contact)
}