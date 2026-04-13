package com.moneromerchant.pos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.moneromerchant.pos.data.local.storage.LocalStorageDataSource
import com.moneromerchant.pos.data.printer.PrinterServiceManager
import com.moneromerchant.pos.data.repository.DataStoreRepository
import com.moneromerchant.pos.data.repository.PrinterRepository
import com.moneromerchant.pos.data.repository.StorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideStorageRepository(localStorageDataSource: LocalStorageDataSource): StorageRepository {
        return StorageRepository(localStorageDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalStorageDataSource(@ApplicationContext context: Context): LocalStorageDataSource {
        return LocalStorageDataSource(context)
    }
}