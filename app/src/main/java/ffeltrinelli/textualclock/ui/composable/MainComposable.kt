package ffeltrinelli.textualclock.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ffeltrinelli.textualclock.domain.clock.TextualClock
import ffeltrinelli.textualclock.model.ClockViewModel
import ffeltrinelli.textualclock.ui.theme.TextualClockTheme
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(modifier: Modifier = Modifier, clockViewModel: ClockViewModel = viewModel()) {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val textualClock: TextualClock by clockViewModel.state()

    TextualClockTheme {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(SettingsDestination) }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Settings"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = ClockDestination) {
                composable<ClockDestination> {
                    ClockScreen(textualClock, Modifier.padding(innerPadding))
                }
                composable<SettingsDestination> {
                    SettingsScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Serializable
object ClockDestination
@Serializable
object SettingsDestination
