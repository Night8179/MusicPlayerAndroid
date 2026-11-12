package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun HomeSearchField(
    fieldState: TextFieldState,
    onSearch: () -> Unit
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        state = fieldState,
        lineLimits = TextFieldLineLimits.SingleLine,
        label = {
            Text(
                text = "Поиск треков"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = onSearch,
                enabled = fieldState.text.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Найти треки"
                )
            }
        }
    )
}
