package com.moneromerchant.pos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.moneromerchant.pos.data.local.datastore.DataStoreLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreLocalDataSource(@ApplicationContext context: Context): DataStoreLocalDataSource {
        return DataStoreLocalDataSource(context)
    }

}