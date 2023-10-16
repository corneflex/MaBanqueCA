package com.scdc.mabanque.features.banks.ui.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.ui.PreviewData
import com.scdc.mabanque.ui.nav.LocalNavController
import com.scdc.mabanque.ui.nav.Screen

@Composable
fun AccountListItem(account: Account) {
    val navController = LocalNavController.current
    AccountListItemClick(account) {
        navController.navigate("${Screen.Account.route}/${account.id}")
    }
}

@Composable
fun AccountListItemClick(account: Account, click:()->Unit = {}) {
    ListItem(modifier = Modifier
        .clickable {
            click()
        },

        headlineContent = { Text(text = account.label) },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${account.balance}€")
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Details",
                    modifier = Modifier.size(16.dp)
                )
            }
        })
}

@Preview
@Composable
fun AccountListItemPreview(){
    AccountListItemClick(account = PreviewData.sampleAccount)
}