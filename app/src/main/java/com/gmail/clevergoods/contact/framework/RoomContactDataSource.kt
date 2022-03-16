package com.gmail.clevergoods.contact.framework

import android.content.Context
import com.gmail.clevergoods.contact.framework.framework.db.ContactEntity
import com.gmail.clevergoods.contact.framework.framework.db.ContactsDatabase
import com.gmail.clevergoods.contacts.data.ItemDataSource
import com.gmail.clevergoods.contacts.domain.Contact


class RoomContactDataSource(val context: Context) : ItemDataSource {

    private val contactDao = ContactsDatabase.getInstance(context).contactDao()

    override suspend fun add(contact: Contact) {
        return contactDao.addItem(
            ContactEntity(0,
                contact.fio,
                contact.phone
            )
        )
    }

    override suspend fun readAll(): List<Contact> = contactDao.getItems().map {
        Contact(
            it.fio,
            it.phone,
            false
        )
    }

    override suspend fun remove(contact: Contact) = contactDao.removeItem(
        ContactEntity(0,
            contact.fio,
            contact.phone
        )
    )
}