package com.scdc.mabanque.features.banks.ui.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.scdc.mabanque.R
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.ui.PreviewData
import com.scdc.mabanque.ui.nav.LocalNavController

@Composable
fun BanksGroup(caBanks: List<Bank>, otherBanks: List<Bank>) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(id = R.string.credit_agricole),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        items(caBanks) {
            BankExpandable(it)
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(id = R.string.other_banks),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        items(otherBanks) {
            BankExpandable(it)
        }

    }
}

@Preview
@Composable
fun BanksGroupPreview() {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
    ){
        BanksGroup(PreviewData.sampleBanksCa,PreviewData.sampleOtherBank)
    }
}