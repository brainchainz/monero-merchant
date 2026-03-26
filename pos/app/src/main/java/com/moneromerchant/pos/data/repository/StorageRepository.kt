package com.moneromerchant.pos.data.repository

import android.net.Uri
import com.moneromerchant.pos.data.local.storage.LocalStorageDataSource
import java.io.File

class StorageRepository(private val localStorageDataSource: LocalStorageDataSource) {

    fun saveImage(uri: Uri, fileName: String): File? {
        return localStorageDataSource.saveImage(uri, fileName)
    }

    fun readImage(fileName: String): File? {
        return localStorageDataSource.readImage(fileName)
    }

    fun deleteImage(fileName: String): Boolean {
        return localStorageDataSource.deleteImage(fileName)
    }
}