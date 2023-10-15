package com.scdc.mabanque.features.banks.ui.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.scdc.core.ui.compose.ExpandableCard
import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.domain.model.Bank

@Composable
fun BankExpandable(bank: Bank) {
    ExpandableCard(
        title = {
            Text(text = bank.name)
            Text(text = "${bank.totalBalance}€")
        },
        content = {
            Column {
                bank.accounts.forEach { it ->
                    AccountListItem(account = it)
                }
            }
        }
    )
}

@Preview
@Composable
fun BankExpandablePreview(){
    BankExpandable(Bank(
        isCA = 1,
        name = "Credit Agricole",
        accounts = listOf(Account(
            balance = 12.34,
            contractNumber = "",
            holder = "",
            id="A",
            label = "My Account",
            operations = emptyList(),
            productCode = "",
            order = 0,
            role = 0
        ),Account(
            balance = 23.52,
            contractNumber = "",
            holder = "",
            id="B",
            label = "My Account",
            operations = emptyList(),
            productCode = "",
            order = 0,
            role = 0
        ))
    ))
}