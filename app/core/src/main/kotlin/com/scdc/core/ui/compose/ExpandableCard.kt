package com.scdc.core.ui.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    var expandedState by rememberSaveable { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clickable { expandedState = !expandedState }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                title()
            }
            IconButton(
                modifier = Modifier
                    .alpha(0.2f)
                    .rotate(rotationState),
                onClick = {
                    expandedState = !expandedState
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                )
            }
        }
        if (expandedState) {
            content()
        }
    }
}


@Preview
@Composable
fun ExpandablePreview() {
    ExpandableCard(
        modifier = Modifier.background(Color.White),
        title = {
            Text(text = "Title")
        },
        content = {
            Text(text = "Pie icing dessert sesame snaps cheesecake caramels jelly. Lemon drops oat cake jelly cookie cake. Biscuit lemon drops lollipop cotton candy carrot cake carrot cake biscuit. Cake cake muffin liquorice marshmallow. Shortbread jelly tart shortbread oat cake marshmallow chocolate bar. Caramels marzipan halvah croissant cotton candy sesame snaps gummi bears. Candy chocolate bar sweet biscuit oat cake candy chocolate. Bonbon croissant jelly liquorice jelly-o jelly-o gummi bears candy. Dragée chocolate bar apple pie macaroon gingerbread cake halvah danish. Candy jelly croissant brownie bear claw chocolate bar. Marzipan bear claw pudding sweet roll caramels lemon drops. Macaroon jelly-o danish cake sweet roll gummi bears dragée cotton candy. Sweet biscuit donut chocolate bar tiramisu candy dessert tart cotton candy")
        })
}