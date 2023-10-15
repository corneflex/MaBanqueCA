package com.scdc.mabanque.ui.nav

import com.scdc.mabanque.R

sealed class BottomNavItem(
    var title: Int,
    var icon: Int,
    var screen: Screen
) {
    object Home :
        BottomNavItem(
            R.string.my_accounts,
            R.drawable.ic_home,
            Screen.Home
        )

    object Simulation :
        BottomNavItem(
            R.string.simulation,
            R.drawable.ic_simu,
            Screen.Simulation
        )

    object Play :
        BottomNavItem(
            R.string.play,
            R.drawable.ic_star,
            Screen.Play
        )

}
