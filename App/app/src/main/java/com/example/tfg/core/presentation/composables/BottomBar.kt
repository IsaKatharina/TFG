package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Border
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.AddButton
import com.example.tfg.core.presentation.buttons.FavsButton
import com.example.tfg.core.presentation.buttons.HomeButton
import com.example.tfg.core.presentation.buttons.YouButton
import com.example.tfg.ui.screens.login.LoginScreen
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.LoginVM

@Composable
fun BottomBar(navController: NavController){

    Row(modifier= Modifier
        .fillMaxWidth()
        .height(75.dp)
        .background(Color.Transparent)
        .border(
            width = 2.dp,
            color = Color(0xFFFF5290)
        ),
        verticalAlignment = Alignment.CenterVertically,

        )

    {
        HomeButton(navController = navController, modifier=Modifier.weight(1f))
        FavsButton(navController = navController, modifier=Modifier.weight(1f))
        AddButton(navController = navController, modifier=Modifier.weight(1f))
        YouButton(navController = navController , modifier=Modifier.weight(1f))

    }

}

@Preview
@Composable
fun PrBottomBar(){
    TFGTheme {

        BottomBar(navController = rememberNavController())

    }
}