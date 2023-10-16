package com.scdc.mabanque.features.banks.ui.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.scdc.core.ui.compose.ExpandableCard
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.ui.PreviewData

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
    BankExpandable(PreviewData.sampleCaBank)
}