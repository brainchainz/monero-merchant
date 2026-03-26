package com.moneromerchant.pos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.moneromerchant.pos.data.printer.PrinterServiceManager
import com.moneromerchant.pos.data.repository.DataStoreRepository
import com.moneromerchant.pos.data.repository.ErrorRepository
import com.moneromerchant.pos.data.repository.PrinterRepository
import com.moneromerchant.pos.data.repository.StorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrinterModule {

    @Provides
    @Singleton
    fun providePrinterRepository(printerServiceManager: PrinterServiceManager, dataStoreRepository: DataStoreRepository, storageRepository: StorageRepository, errorRepository: ErrorRepository): PrinterRepository {
        return PrinterRepository(printerServiceManager, dataStoreRepository, storageRepository, errorRepository)
    }

    @Provides
    @Singleton
    fun providePrinterServiceManager(@ApplicationContext applicationContext: Context, dataStoreRepository: DataStoreRepository): PrinterServiceManager {
        return PrinterServiceManager(applicationContext, dataStoreRepository)
    }
}