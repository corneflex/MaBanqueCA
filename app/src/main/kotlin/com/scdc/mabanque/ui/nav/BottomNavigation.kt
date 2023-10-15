package com.scdc.mabanque.ui.nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController

@Composable
fun BottomNavigation(nav: NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Simulation,
        BottomNavItem.Play
    )

    val selectedItem = remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            AddItem(
                selected = selectedItem.intValue == index,
                onClick = {
                    nav.navigate(item.screen.route)
                    selectedItem.intValue = index
                },
                navItem = item
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    selected: Boolean,
    onClick: () -> Unit,
    navItem: BottomNavItem
) {
    NavigationBarItem(
        label = {
            Text(text = stringResource(id = navItem.title))
        },

        icon = {
            Icon(
                painterResource(id = navItem.icon),
                contentDescription = stringResource(id = navItem.title)
            )
        },
        selected = selected,
        alwaysShowLabel = true,
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors()
    )
}