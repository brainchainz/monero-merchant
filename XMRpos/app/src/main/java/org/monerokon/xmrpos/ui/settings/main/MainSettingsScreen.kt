// MainSettingsScreen.kt
package org.monerokon.xmrpos.ui.settings.main

import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.monerokon.xmrpos.R


@Composable
fun MainSettingsScreenRoot(viewModel: MainSettingsViewModel, navController: NavHostController) {
    viewModel.setNavController(navController)
    MainSettingsScreen(
        onBackClick = viewModel::navigateToPayment,
        navigateToCompanyInformation = viewModel::navigateToCompanyInformation,
        navigateToFiatCurrencies = viewModel::navigateToFiatCurrencies,
        navigateToSecurity = viewModel::navigateToSecurity,
        navigateToTransactionHistory = viewModel::navigateToTransactionHistory,
        navigateToBackend = viewModel::navigateToBackend,
        navigateToPrinterSettings = viewModel::navigateToPrinterSettings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSettingsScreen(
    onBackClick: () -> Unit,
    navigateToCompanyInformation: () -> Unit,
    navigateToFiatCurrencies: () -> Unit,
    navigateToSecurity: () -> Unit,
    navigateToTransactionHistory: () -> Unit,
    navigateToBackend: () -> Unit,
    navigateToPrinterSettings: () -> Unit
) {
    val context = LocalContext.current
    val versionName = remember(context.packageName) {
        try {
            val packageManager = context.packageManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager
                    .getPackageInfo(
                        context.packageName,
                        PackageManager.PackageInfoFlags.of(0)
                    ).versionName.orEmpty()
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(context.packageName, 0)?.versionName.orEmpty()
            }
        } catch (ignored: PackageManager.NameNotFoundException) {
            ""
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_24px),
                            contentDescription = "Go back to previous screen"
                        )
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Settings")
                        Text("v$versionName")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            SettingsCard(text = "Company information", onClick = {navigateToCompanyInformation()})
            Spacer(modifier = Modifier.height(24.dp))
            SettingsCard(text = "Fiat currencies", onClick = {navigateToFiatCurrencies()})
            Spacer(modifier = Modifier.height(24.dp))
            SettingsCard(text = "Security", onClick = {navigateToSecurity()})
            Spacer(modifier = Modifier.height(24.dp))
            SettingsCard(text = "Transaction history", onClick = {navigateToTransactionHistory()})
            Spacer(modifier = Modifier.height(24.dp))
            SettingsCard(text = "Backend", onClick = {navigateToBackend()})
            Spacer(modifier = Modifier.height(24.dp))
            SettingsCard(text = "Printer settings", onClick = {navigateToPrinterSettings()})
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsCard(
    text: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_24px),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
