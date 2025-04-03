package com.jumbo.flutterembedder

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.fragment.compose.AndroidFragment
import io.flutter.embedding.android.FlutterActivity

class MainActivity : FragmentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("embedding demo")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = selectedIndex == 0,
                                    onClick = { selectedIndex = 0 },
                                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
                                )
                                NavigationBarItem(
                                    selected = selectedIndex == 1,
                                    onClick = { selectedIndex = 1 },
                                    icon = {
                                        Icon(
                                            Icons.Filled.SportsBaseball,
                                            contentDescription = "Ball"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = selectedIndex == 2,
                                    onClick = { selectedIndex = 2 },
                                    icon = {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = "Starfield"
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        when (selectedIndex) {
                            0 -> {
                                LaunchContent(
                                    onLaunch = {
                                        startActivity(
                                            FlutterActivity.createDefaultIntent(context)
                                        )
                                    },
                                    modifier = Modifier.padding(16.dp)
                                )
                            }

                            1 -> {
                                EmbeddedFragmentBallContent(modifier = Modifier.fillMaxSize())
                            }

                            2 -> {
                                EmbeddedFragmentStarfieldContent(modifier = Modifier.fillMaxSize())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LaunchContent(
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentOnLaunch by rememberUpdatedState(onLaunch)
    Column(modifier) {
        Button(
            onClick = {
                currentOnLaunch()
            }
        ) {
            Text("Start Flutter")
        }
    }
}

@Composable
private fun EmbeddedFragmentBallContent(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AndroidFragment<BallFlutterFragment>(modifier = Modifier.fillMaxSize()) {
            BallFlutterFragment()
        }
    }
}

@Composable
private fun EmbeddedFragmentStarfieldContent(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AndroidFragment<StarfieldFlutterFragment>(modifier = Modifier.fillMaxSize()) {
            StarfieldFlutterFragment()
        }
    }
}
