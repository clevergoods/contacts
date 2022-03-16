package com.gmail.clevergoods.contact.framework.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {

  companion object {

    private const val DATABASE_NAME = "contact.db"

    private var instance: ContactsDatabase? = null

    private fun create(context: Context): ContactsDatabase =
        Room.databaseBuilder(context, ContactsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    fun getInstance(context: Context): ContactsDatabase =
        (instance ?: create(context)).also { instance = it }
  }

  abstract fun contactDao(): ContactDao

}