package io.github.night8179.feature.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(onOpenTrackCard: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text("HomeScreen")
            Button(onClick = {
                onOpenTrackCard()
            }) {
                Text("Press")
            }
        }
    }
}