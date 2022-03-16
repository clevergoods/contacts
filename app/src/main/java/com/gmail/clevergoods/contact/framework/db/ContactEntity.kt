package com.gmail.clevergoods.contact.framework.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.clevergoods.contacts.domain.Contact
import java.io.Serializable

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "fio") val fio: String,
    @ColumnInfo(name = "phone") val phone: String
) : Serializable {
    companion object {
        val EMPTY = ContactEntity(0, "", "")
    }
}

