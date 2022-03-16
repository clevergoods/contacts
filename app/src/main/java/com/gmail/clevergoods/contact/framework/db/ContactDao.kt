package com.gmail.clevergoods.contact.framework.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gmail.clevergoods.contacts.domain.Contact

@Dao
interface ContactDao {

  @Insert(onConflict = REPLACE)
  suspend fun addItem(contact: ContactEntity)

  @Query("SELECT * FROM contacts")
  suspend fun getItems(): List<ContactEntity>

  @Delete
  suspend fun removeItem(contact: ContactEntity)
}
