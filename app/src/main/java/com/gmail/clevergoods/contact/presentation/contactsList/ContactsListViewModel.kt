/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.gmail.clevergoods.contact.presentation.contactsList

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.clevergoods.contact.framework.Interactors
import com.gmail.clevergoods.contact.framework.ContactsViewModel
import com.gmail.clevergoods.contacts.domain.Contact
import kotlinx.coroutines.*

class ContactsListViewModel(application: Application, interactors: Interactors)
  : ContactsViewModel(application, interactors) {

  val items: MutableLiveData<List<Contact>> = MutableLiveData()
  val selectedItems: MutableLiveData<Set<Int>> = MutableLiveData(HashSet())

  fun loadItems() {
    viewModelScope.launch {
      val contacts = interactors.getContacts()
      val positions = selectedItems.value
      positions?.let {
        for (i in contacts.indices) {
          if(it.contains(i)) {
            contacts[i].isSelected = it.contains(i)
          }else{
            contacts[i].isSelected = it.contains(i)
          }
        }
      }
      items.postValue(contacts)
    }
  }

  fun setSelectedItem(position: Int){
    viewModelScope.launch {
      val contacts = items.value
      val selected = selectedItems.value as HashSet<Int>
      val isSelected = contacts?.get(position)?.isSelected
      isSelected?.let{
        contacts[position].isSelected = !it
        if(!it) {
          selected.add(position)
        }else{
          selected.remove(position)
        }
       selectedItems.postValue(selected)
      }
    }
  }

  fun addItem(contact: Contact) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        interactors.addContact(contact)
      }
      //loadItems()
    }
  }
}
