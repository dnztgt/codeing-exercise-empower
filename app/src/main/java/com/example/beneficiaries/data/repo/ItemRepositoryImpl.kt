package com.example.beneficiaries.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beneficiaries.data.ItemRepository
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.data.util.getJsonFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ItemRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
) : ItemRepository {
    private val items = MutableLiveData<List<Beneficiary>>()

    override suspend fun populate(context: Context) {
        withContext(ioDispatcher) {
            val jsonFileString = getJsonFromAssets(context, "Beneficiaries.json")

            val gson = Gson()
            val listItemType = object : TypeToken<List<Beneficiary?>?>() {}.type

            val newItems = gson.fromJson<List<Beneficiary>>(jsonFileString, listItemType)
            items.postValue(newItems)
        }
    }

    override suspend fun getItemBySecurityNumber(securityNumber: String): Beneficiary? {
        return withContext(ioDispatcher) {
            items.value?.find { it.socialSecurityNumber == securityNumber }
        }
    }

    override fun getItems(): LiveData<List<Beneficiary>> {
        return items
    }
}
