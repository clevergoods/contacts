package com.gmail.clevergoods.contacts.domain
import java.io.Serializable

data class Contact(
    val fio: String,
    val phone: String,
    var isSelected:Boolean
): Serializable {
    companion object {
        val EMPTY = Contact("", "", false)
    }
}
