package com.moneromerchant.pos.data.remote.exchangeRate

import com.moneromerchant.pos.data.remote.exchangeRate.model.ExchangeRateResponse
import com.moneromerchant.pos.shared.DataResult
import javax.inject.Inject

class ExchangeRateRemoteDataSource @Inject constructor(
    private val api: ExchangeRateApi
) {
    suspend fun fetchExchangeRates(fromSymbol: String, toSymbols: List<String>): DataResult<ExchangeRateResponse> {
        return try {
            val response = api.fetchExchangeRates(fromSymbol, toSymbols.joinToString(","))
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Failure(message = e.message ?: "Unknown error")
        }
    }
}