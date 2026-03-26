// NavGraph.kt
package com.moneromerchant.pos.ui

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.moneromerchant.pos.ui.common.dialogs.error.ErrorDialog
import com.moneromerchant.pos.ui.payment.checkout.PaymentCheckoutScreenRoot
import com.moneromerchant.pos.ui.payment.checkout.PaymentCheckoutViewModel
import com.moneromerchant.pos.ui.payment.entry.PaymentEntryScreenRoot
import com.moneromerchant.pos.ui.payment.entry.PaymentEntryViewModel
import com.moneromerchant.pos.ui.payment.login.LoginScreenRoot
import com.moneromerchant.pos.ui.payment.login.LoginViewModel
import com.moneromerchant.pos.ui.payment.notification.NotificationDialog
import com.moneromerchant.pos.ui.payment.success.PaymentSuccessScreenRoot
import com.moneromerchant.pos.ui.payment.success.PaymentSuccessViewModel
import com.moneromerchant.pos.ui.settings.backend.BackendScreenRoot
import com.moneromerchant.pos.ui.settings.backend.BackendViewModel
import com.moneromerchant.pos.ui.settings.balance.BalanceScreenRoot
import com.moneromerchant.pos.ui.settings.balance.BalanceViewModel
import com.moneromerchant.pos.ui.settings.companyinformation.CompanyInformationScreenRoot
import com.moneromerchant.pos.ui.settings.companyinformation.CompanyInformationViewModel
import com.moneromerchant.pos.ui.settings.fiatcurrencies.FiatCurrenciesScreenRoot
import com.moneromerchant.pos.ui.settings.fiatcurrencies.FiatCurrenciesViewModel
import com.moneromerchant.pos.ui.settings.main.MainSettingsScreenRoot
import com.moneromerchant.pos.ui.settings.main.MainSettingsViewModel
import com.moneromerchant.pos.ui.settings.moneropay.SecurityScreenRoot
import com.moneromerchant.pos.ui.settings.moneropay.SecurityViewModel
import com.moneromerchant.pos.ui.settings.printersettings.PrinterSettingsScreenRoot
import com.moneromerchant.pos.ui.settings.printersettings.PrinterSettingsViewModel
import com.moneromerchant.pos.ui.settings.transactionhistory.TransactionHistoryScreenRoot
import com.moneromerchant.pos.ui.settings.transactionhistory.TransactionHistoryViewModel

@Composable
fun NavGraphRoot(
    navController: NavHostController = rememberNavController(),
    startDestination: Any,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ) + fadeIn(
                initialAlpha = 0.5f,
                animationSpec = tween(200, easing = EaseOut)
            )
        },
        exitTransition = {
            scaleOut(
                targetScale = 0.95f,
                animationSpec = tween(200, easing = EaseOut)
            ) + fadeOut(
                targetAlpha = 0.7f,
                animationSpec = tween(200)
            )
        },
        popEnterTransition = {
            scaleIn(
                initialScale = 0.95f,
                animationSpec = tween(200, easing = EaseOut)
            ) + fadeIn(
                initialAlpha = 0.7f,
                animationSpec = tween(200)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ) + fadeOut(
                targetAlpha = 0.5f,
                animationSpec = tween(150)
            )
        }
    ) {
        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreenRoot(viewModel = loginViewModel, navController = navController)
        }
        composable<PaymentEntry> {
            val paymentEntryViewModel: PaymentEntryViewModel =  hiltViewModel()
            PaymentEntryScreenRoot(
                viewModel = paymentEntryViewModel,
                navController = navController
            )
        }
        composable<PaymentCheckout> {
            val args = it.toRoute<PaymentCheckout>()
            val paymentCheckoutViewModel: PaymentCheckoutViewModel = hiltViewModel()
            PaymentCheckoutScreenRoot(viewModel = paymentCheckoutViewModel, navController = navController, fiatAmount = args.fiatAmount, primaryFiatCurrency = args.primaryFiatCurrency)
        }
        composable<PaymentSuccess> {
            val args = it.toRoute<PaymentSuccess>()
            val paymentSuccessViewModel: PaymentSuccessViewModel = hiltViewModel()
            PaymentSuccessScreenRoot(viewModel = paymentSuccessViewModel, navController = navController, fiatAmount = args.fiatAmount, primaryFiatCurrency = args.primaryFiatCurrency, txId = args.txId, xmrAmount = args.xmrAmount, exchangeRate = args.exchangeRate, timestamp = args.timestamp, showPrintReceipt = args.showPrintReceipt)
        }
        composable<Settings> {
            val mainSettingsViewModel: MainSettingsViewModel = viewModel()
            MainSettingsScreenRoot(viewModel = mainSettingsViewModel, navController = navController)
        }
        composable<CompanyInformation> {
            val companyInformationViewModel: CompanyInformationViewModel = hiltViewModel()
            CompanyInformationScreenRoot(viewModel = companyInformationViewModel, navController = navController)
        }
        composable<FiatCurrencies> {
            val fiatCurrenciesViewModel: FiatCurrenciesViewModel = hiltViewModel()
            FiatCurrenciesScreenRoot(viewModel = fiatCurrenciesViewModel, navController = navController)
        }
        composable<Security> {
            val securityViewModel: SecurityViewModel = hiltViewModel()
            SecurityScreenRoot(viewModel = securityViewModel, navController = navController)
        }
        composable<TransactionHistory> {
            val transactionHistoryViewModel: TransactionHistoryViewModel = hiltViewModel()
            TransactionHistoryScreenRoot(viewModel = transactionHistoryViewModel, navController = navController)
        }
        composable<Backend> {
            val backendViewModel: BackendViewModel = hiltViewModel()
            BackendScreenRoot(viewModel = backendViewModel, navController = navController)
        }
        composable<PrinterSettings> {
            val printerSettingsViewModel: PrinterSettingsViewModel = hiltViewModel()
            PrinterSettingsScreenRoot(viewModel = printerSettingsViewModel, navController = navController)
        }
        composable<Balance> {
            val balanceViewModel: BalanceViewModel = hiltViewModel()
            BalanceScreenRoot(viewModel = balanceViewModel, navController = navController)
        }
    }
    NotificationDialog(
        navController = navController,
        suppressedRoutes = setOf(PaymentCheckout::class.qualifiedName, PaymentSuccess::class.qualifiedName) as Set<String>
    )
    ErrorDialog()
}

@Serializable
object Login

@Serializable
object PaymentEntry

@Serializable
data class PaymentCheckout(
    val fiatAmount: Double,
    val primaryFiatCurrency: String,
)

@Serializable
data class PaymentSuccess(
    val fiatAmount: Double,
    val primaryFiatCurrency: String,
    val txId: String,
    val xmrAmount: Double,
    val exchangeRate: Double,
    val timestamp: String,
    val showPrintReceipt: Boolean,
)

// Settings routes

@Serializable
object Settings

@Serializable
object CompanyInformation

@Serializable
object FiatCurrencies

@Serializable
object Security

@Serializable
object TransactionHistory

@Serializable
object Backend

@Serializable
object PrinterSettings

@Serializable
object Balance
