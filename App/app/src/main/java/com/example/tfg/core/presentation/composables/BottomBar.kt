package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.core.presentation.buttons.AddButton
import com.example.tfg.core.presentation.buttons.FavsButton
import com.example.tfg.core.presentation.buttons.HomeButton
import com.example.tfg.core.presentation.buttons.YouButton
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun BottomBar(navController: NavController){

    Row (modifier= Modifier
        .fillMaxWidth()
        .height(75.dp)
        .background(Color(0xFFFF5290)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        )

    {
      HomeButton(navController = navController)
        AddButton(navController = navController)
        YouButton(navController = navController)

    }

}


//fun PrBottomBar(){
//    TFGTheme {
//
//        BottomBar(navController = rememberNavController())
//
//    }
//}

