package com.example.beneficiaries.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.beneficiaries.MyApplication
import com.example.beneficiaries.data.ItemRepository
import com.example.beneficiaries.data.model.Beneficiary

class MainViewModel(
    itemRepository: ItemRepository,
) : ViewModel() {

    private val _items = itemRepository.getItems() as MutableLiveData
    val items: LiveData<List<Beneficiary>> = _items

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyApplication

                return MainViewModel(application.itemRepo) as T
            }
        }
    }
}
