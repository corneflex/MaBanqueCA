package com.scdc.mabanque.features.banks.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.scdc.core.ui.compose.Header
import com.scdc.mabanque.features.banks.ui.compose.component.OperationListItem
import com.scdc.mabanque.features.banks.ui.viewmodel.BankViewModel

@Composable
fun AccountScreen(accountId: String, bankViewModel: BankViewModel = hiltViewModel()) {

    val state = bankViewModel.state.value
    val account = state.accounts[accountId]

    /* val sortedOperations = remember(operations) {
         operations.sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })
     }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        account?.apply {
            Header(title = "${account.balance}€", subTitle = account.label)
            LazyColumn {
                items(operations) {
                    OperationListItem(operation = it)
                }
            }
        }
    }
}