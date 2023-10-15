package com.scdc.core.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header(title: String, subTitle:String) {
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text=title, style= MaterialTheme.typography.headlineLarge)
        Text(text=subTitle, style= MaterialTheme.typography.headlineMedium)
    }
}

@Preview
@Composable
fun HeaderPreview(){
    Header(title = "hello", subTitle = "world" )
}