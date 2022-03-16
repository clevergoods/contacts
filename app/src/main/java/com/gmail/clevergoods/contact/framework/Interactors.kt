package com.gmail.clevergoods.contact.framework

import com.gmail.clevergoods.contacts.interactors.*

data class Interactors(
    val addContact: AddContact,
    val getContacts: GetContacts,
    val removeContact: RemoveContact
)
