package com.example.tfg.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.navigation.AppScreens
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.core.presentation.composables.ProductCard
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.ui.theme.backgroundLight


@Composable
fun StartScreen(modifier: Modifier, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()
        .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier=Modifier.padding(0.dp,100.dp,0.dp,0.dp)) {
                HeaderImagen(modifier = modifier, navController = navController)

            }
            Row() {
                ElevatedButton(
                    onClick = { navController.navigate(AppScreens.LoginScreen.route) },
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5290)
                    ),
                    modifier = Modifier.fillMaxWidth().padding(20.dp,0.dp,20.dp,0.dp)
                ) {
                    Text(text = "Login", fontSize = 28.sp)

                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Or click ", color = Color(0xFFFF5290), fontSize = 24.sp)

                Button (
                    onClick = { navController.navigate(AppScreens.NewUserScreen.route)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5290)
                    )){
                    Text(text = "here")
                }

                Text(text = " if you're new", color = Color(0xFFFF5290), fontSize = 24.sp)
            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun PCPre(){
    TFGTheme {
        StartScreen(modifier = Modifier, navController = rememberNavController())
    }
}



