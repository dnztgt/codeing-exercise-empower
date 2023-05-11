package com.example.beneficiaries

import android.app.Application
import com.example.beneficiaries.data.ItemRepository
import com.example.beneficiaries.data.repo.ItemRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MyApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    val itemRepo: ItemRepository by lazy {
        ItemRepositoryImpl(Dispatchers.IO)
    }

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            itemRepo.populate(applicationContext)
        }
    }
}
