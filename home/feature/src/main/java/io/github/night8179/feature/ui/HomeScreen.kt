package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import io.github.night8179.feature.HomeInteractions

@Composable
internal fun HomeScreen(
    interaction: HomeInteractions
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "HomeScreen"
            )
            Button(onClick = {
                interaction.onTrackClick()
            }) {
                Text(
                    text = "Press"
                )
            }
        }
    }
}
