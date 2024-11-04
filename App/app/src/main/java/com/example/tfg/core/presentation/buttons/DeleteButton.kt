package com.example.tfg.core.presentation.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.material3.IconButton
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens

@Composable
fun DeleteButton(navController: NavController) {
    IconButton(onClick = { TODO() }) {
        Icon(
            painter= painterResource(id = R.drawable.delete_icon),
            contentDescription = "EditProfile",
            modifier= Modifier.size(35.dp)
        )

    }
}

//TODO:hay que hacer una ventana emergente