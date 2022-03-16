package com.gmail.clevergoods.contact.framework

import android.content.Context
import com.gmail.clevergoods.contact.framework.framework.db.ContactEntity
import com.gmail.clevergoods.contact.framework.framework.db.ContactsDatabase
import com.gmail.clevergoods.contacts.data.ItemDataSource
import com.gmail.clevergoods.contacts.domain.Contact


class MemoryContactDataSource(val context: Context) : ItemDataSource {

    private val contactDao = ContactsDatabase.getInstance(context).contactDao()

    override suspend fun add(contact: Contact) {
        return contactDao.addItem(
            ContactEntity(0,
                contact.fio,
                contact.phone
            )
        )
    }

    override suspend fun readAll(): List<Contact> {
        val fioList = arrayOf(
            "Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина"
        )
        val phoneList = arrayOf(
            "1111", "22222", "33333",
            "44444", "55555", "66666", "777777"
        )

        val data = ArrayList<Contact>()

        //FILL
        for (i in fioList.indices) {
            data.add(Contact(fioList.get(i), phoneList.get(i), false))
        }
        return data
    }


    override suspend fun remove(contact: Contact) = contactDao.removeItem(
        ContactEntity(0,
            contact.fio,
            contact.phone
        )
    )
}