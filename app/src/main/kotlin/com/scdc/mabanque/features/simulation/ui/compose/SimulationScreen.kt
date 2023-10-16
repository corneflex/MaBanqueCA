package com.scdc.mabanque.features.simulation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scdc.mabanque.R


@Composable
fun SimulationScreen() {

    Column(modifier = Modifier.fillMaxSize().background(color= MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.ic_simu),
            contentDescription = null,
            modifier = Modifier.size(24.dp)

        )
        Text(text = stringResource(id = R.string.simulation))
    }
}

@Preview
@Composable
fun PlayScreenPreview(){
    SimulationScreen()
}