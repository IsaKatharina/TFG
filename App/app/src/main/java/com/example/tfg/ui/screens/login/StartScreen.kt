package com.example.tfg.ui.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.navigation.AppScreens
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.core.presentation.composables.ProductCard
import com.example.tfg.dal.connectivity.ConnectivityObserver
import com.example.tfg.dal.connectivity.NetworkConnectivityObserver
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.ui.theme.backgroundLight


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StartScreen(modifier: Modifier, navController: NavController) {

    val context= LocalContext.current
    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Available)

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
            //chequea si hay conexión, si no hay, no se puede seguir con la app.
            if (status.name!="Available") {

                Log.i("sos", "$status.name")

//        //TODO:poner imagen de que no hay internet
//        Toast.makeText(
//            LocalContext.current, // Obtenemos el contexto actual
//            "No hay conexión a internet, no puede iniciar sesión",
//            Toast.LENGTH_SHORT
//        ).show()

                Column {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painterResource(id = R.drawable.nowifi),
                            contentDescription = "no wifi available",
                            tint = Color(0xFFFF5290),
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

            }else {
                Row() {
                    ElevatedButton(
                        onClick = { navController.navigate(AppScreens.LoginScreen.route) },
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5290)
                        ),
                        modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp)
                    ) {
                        Text(text = "Login", fontSize = 28.sp)

                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Or click ", color = Color(0xFFFF5290), fontSize = 24.sp)

                    Button(
                        onClick = { navController.navigate(AppScreens.NewUserScreen.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5290)
                        )
                    ) {
                        Text(text = "here")
                    }

                    Text(text = " if you're new", color = Color(0xFFFF5290), fontSize = 24.sp)
                }
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



