package io.github.night8179.musicplayer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

internal data class NavTab(
    val route: Route,
    val labelRes: String,
    val icon: ImageVector
)
