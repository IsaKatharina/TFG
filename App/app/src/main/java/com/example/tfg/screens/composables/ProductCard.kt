package com.example.tfg.screens.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.screens.StartScreen
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProductCard(modifier: Modifier, navController: NavController){
    ElevatedCard (modifier=Modifier.size(25.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(Color(0xFFFF5290)),
        shape = CardDefaults.outlinedShape
    ){
        Text(text = "This is a product")


    }
}

