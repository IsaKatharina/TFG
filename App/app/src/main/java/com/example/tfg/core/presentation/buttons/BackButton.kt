package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun BackButton(navController: NavController, modifier: Modifier){

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter= painterResource(id = R.drawable.back_pink),
            contentDescription = "Back",
            tint= Color(0xFFFF5290),
            modifier=Modifier.size(35.dp)
        )
    }
}
