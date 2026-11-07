package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FavouriteScreen() {
    Row {
        Column {
            Text(
                text = "FavouriteScreen"
            )
            Button(onClick = {
                Unit
            }) {
                Text(
                    text = "Press"
                )
            }
        }
    }
}
