package com.example.tfg.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.tfg.screens.buttons.HeartButton
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProductCardTest(modifier: Modifier) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 100.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"",
                    color = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.TopEnd)
                .zIndex(1f) // Asegura que el botón esté por encima del texto
        ) {
            HeartButton(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun Testeo(){
    TFGTheme {
        ProductCardTest(modifier = Modifier)
    }
}