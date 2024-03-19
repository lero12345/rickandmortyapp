package com.barquero.rickandmorty.ui.util.preview

import androidx.compose.runtime.Composable
import com.barquero.rickandmorty.ui.theme.AppTheme

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    AppTheme {
        content()
    }
}