package com.example.beneficiaries.ui.details

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.beneficiaries.MyApplication
import com.example.beneficiaries.data.ItemRepository
import com.example.beneficiaries.data.model.Beneficiary
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val itemRepository: ItemRepository,
) : ViewModel() {
    private val _item = MutableLiveData<Beneficiary>()
    val item: LiveData<Beneficiary> = _item

    fun getItemBySecurityNumber(securityNumber: String) {
        viewModelScope.launch {
            val curItem = itemRepository.getItemBySecurityNumber(securityNumber)
            curItem?.let { _item.postValue(it) }
        }
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as MyApplication

                return DetailsViewModel(application.itemRepo) as T
            }
        }
    }
}
