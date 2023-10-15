package com.scdc.mabanque.features.banks.ui.compose.component

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.scdc.mabanque.features.banks.domain.model.Operation
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun OperationListItem(operation: Operation){
    val formattedDate = remember(operation.date) {
        val pattern = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.format(operation.date) // Assuming operation.date is a Date object
    }

    ListItem(
        headlineContent = { Text(text = operation.title) },
        supportingContent = {Text(text = formattedDate)},
        trailingContent = {
                Text(text = "${operation.amount}€")
        })
}