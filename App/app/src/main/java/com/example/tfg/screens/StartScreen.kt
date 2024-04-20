package com.example.tfg.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg.ui.theme.TFGTheme


@Composable
fun StartScreen(navController: NavController,modifier: Modifier = Modifier){
    Surface (color= MaterialTheme.colorScheme.background){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize() ,

            ) {
            Text(text = "BestDupe",
                modifier= Modifier.padding(70.dp))

            Button(onClick = { /*TODO*/ },
                enabled= true,
                shape = RoundedCornerShape(5.dp),

                ) {
                Text(text = "Login")

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)

            ) {
                Text(text = "Or click ")

                ClickableText(text = AnnotatedString("here"), onClick = { offset ->
                    annonatedString.getStringAnnotations (

                    )

                }  )
                Text(text = " if you're new")
            }


        }
    }


}
