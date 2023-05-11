package com.example.beneficiaries.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.beneficiaries.data.model.Beneficiary

interface ItemRepository {

    fun getItems(): LiveData<List<Beneficiary>>

    suspend fun populate(context: Context)

    suspend fun getItemBySecurityNumber(securityNumber: String): Beneficiary?
}
