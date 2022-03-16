package com.gmail.clevergoods.contacts.data

import com.gmail.clevergoods.contacts.domain.Contact

interface ItemDataSource {
    suspend fun add(contact: Contact)

    suspend fun readAll(): List<Contact>

    suspend fun remove(contact: Contact)
}