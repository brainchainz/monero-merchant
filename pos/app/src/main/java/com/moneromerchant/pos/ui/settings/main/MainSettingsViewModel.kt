// MainSettingsViewModel.kt
package com.moneromerchant.pos.ui.settings.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.moneromerchant.pos.ui.Backend
import com.moneromerchant.pos.ui.Balance
import com.moneromerchant.pos.ui.CompanyInformation
import com.moneromerchant.pos.ui.FiatCurrencies
import com.moneromerchant.pos.ui.PaymentEntry
import com.moneromerchant.pos.ui.PrinterSettings
import com.moneromerchant.pos.ui.Security
import com.moneromerchant.pos.ui.TransactionHistory

class MainSettingsViewModel (private val savedStateHandle: SavedStateHandle): ViewModel() {

    private var navController: NavHostController? = null

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun navigateToPayment() {
        navController?.popBackStack()
    }

    fun navigateToCompanyInformation() {
        navController?.navigate(CompanyInformation)
    }

    fun navigateToFiatCurrencies() {
        navController?.navigate(FiatCurrencies)
    }

    fun navigateToSecurity() {
        navController?.navigate(Security)
    }

    fun navigateToTransactionHistory() {
        navController?.navigate(TransactionHistory)
    }

    fun navigateToBackend() {
        navController?.navigate(Backend)
    }

    fun navigateToPrinterSettings() {
        navController?.navigate(PrinterSettings)
    }

    fun navigateToBalance() {
        navController?.navigate(Balance)
    }
}


