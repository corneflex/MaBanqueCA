package com.scdc.mabanque.features.banks.ui.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scdc.mabanque.features.banks.ui.compose.component.BanksGroup
import com.scdc.mabanque.features.banks.ui.viewmodel.BankViewModel


@Composable
fun BanksScreen(bankViewModel: BankViewModel = hiltViewModel()) {

    val state = bankViewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(bankViewModel.failure.value) { // This block will be launched every time state changes
        bankViewModel.failure.value.error?.let {
            Toast.makeText(
                context,
                "There was an error: ${it.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        if (state.isLoading)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    trackColor = MaterialTheme.colorScheme.secondary,
                )
            }
        else
            BanksGroup(caBanks = state.caBanks, otherBanks = state.otherBanks)

    }

}