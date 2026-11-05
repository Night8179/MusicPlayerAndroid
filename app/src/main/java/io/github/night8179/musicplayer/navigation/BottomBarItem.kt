package io.github.night8179.musicplayer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomBarItem(
        label = "Home",
        icon = Icons.Default.House
    )

    data object Random : BottomBarItem(
        label = "Random",
        icon = Icons.Default.Shuffle
    )

    data object Favourite : BottomBarItem(
        label = "Favourite",
        icon = Icons.Default.Star
    )
}
