package com.moneromerchant.pos.data.repository

import android.content.Context
import android.content.Intent
import com.moneromerchant.pos.data.hce.HceServiceManager
import javax.inject.Inject

class HceRepository @Inject constructor(
    private val context: Context
) {

    fun updateUri(uri: String) {
        val intent = Intent(context, HceServiceManager::class.java)
        intent.putExtra("uri", uri)
        context.startService(intent)
    }

}