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
fun EditProfileButton(navController: NavController) {

    IconButton(onClick = {navController.navigate(AppScreens.EditProfileScreen.route)}) {
        Icon(
            painter= painterResource(id = R.drawable.right_arrow),
            contentDescription = "EditProfile",
            tint= Color(0xFFFF5290),
            modifier= Modifier.size(35.dp)
        )

    }


}