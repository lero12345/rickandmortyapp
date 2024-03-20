package com.barquero.rickandmorty.presentation.util.preview

import androidx.compose.runtime.Composable
import com.barquero.rickandmorty.presentation.ui.theme.AppTheme

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    AppTheme {
        content()
    }
}