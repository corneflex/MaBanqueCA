package com.scdc.mabanque.ui.nav

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.scdc.mabanque.R
import com.scdc.mabanque.features.banks.ui.compose.AccountScreen
import com.scdc.mabanque.features.banks.ui.compose.BanksScreen
import com.scdc.mabanque.features.play.ui.compose.PlayScreen
import com.scdc.mabanque.features.simulation.ui.compose.SimulationScreen

@Immutable
data class ScaffoldViewState(
    @StringRes val topAppBarTitle: Int? = null,
)

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    var scaffoldViewState by remember {
        mutableStateOf(ScaffoldViewState())
    }

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            val isBackButtonVisible by remember {
                derivedStateOf {
                    //Should have implemented a toolbar controller to display a toolbar depending on the route
                    // The ToolBarController should be passed to the Navigation component fun Navigation(toolbarController: ToolBarController)
                    currentBackStackEntry?.destination?.route == "account/{id}"
                }
            }
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    scaffoldViewState.topAppBarTitle?.let {
                        Text(text = stringResource(id = it))
                    }
                },
                navigationIcon = if (isBackButtonVisible) {
                    {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    {}
                }

            )
        },
        bottomBar = {
            BottomNavigation(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CompositionLocalProvider(LocalNavController provides navController) {
                NavHost(
                    navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    slideComposable(Screen.Home.route) {
                        LaunchedEffect(Unit) {
                            scaffoldViewState =
                                ScaffoldViewState(topAppBarTitle = R.string.my_accounts)
                        }
                        BanksScreen()
                    }
                    slideComposable(
                        "${Screen.Account.route}/{id}"
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        id?.let { AccountScreen(id) }
                    }
                    slideComposable(Screen.Simulation.route) {
                        LaunchedEffect(Unit) {
                            scaffoldViewState =
                                ScaffoldViewState(topAppBarTitle = R.string.simulation)
                        }
                        SimulationScreen()
                    }
                    slideComposable(Screen.Play.route) {
                        LaunchedEffect(Unit) {
                            scaffoldViewState = ScaffoldViewState(topAppBarTitle = R.string.play)
                        }
                        PlayScreen()
                    }
                }
            }
        }
    }

}