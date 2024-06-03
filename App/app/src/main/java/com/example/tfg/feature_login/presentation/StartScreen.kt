package com.example.tfg.feature_login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tfg.navigation.AppScreens
import com.example.tfg.core.presentation.composables.HeaderImagen


@Composable
fun StartScreen(modifier: Modifier, navController: NavController){
    Surface (color= MaterialTheme.colorScheme.background){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
            ) {
            HeaderImagen(modifier = modifier, navController= navController)

            Button(onClick = {navController.navigate(AppScreens.LoginScreen.route)},
                enabled= true,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5290)
                )

                ) {
                Text(text = "Login")

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)

            ) {
                Text(text = "Or click ", color = Color(0xFFFF5290))

                ClickableText(text = AnnotatedString("here"), onClick = {navController.navigate(AppScreens.NewUserScreen.route)}, )

                }
                Text(text = " if you're new", color= Color(0xFFFF5290))
            }


        }
    }




