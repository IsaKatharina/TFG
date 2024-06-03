package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.screens.StartScreen
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun HeaderImagen(modifier: Modifier, navController: NavController) {
    Image(painter= painterResource(id = R.drawable.bestdupe__1_), contentDescription = "logo_blanco", modifier= Modifier.fillMaxWidth())
}



