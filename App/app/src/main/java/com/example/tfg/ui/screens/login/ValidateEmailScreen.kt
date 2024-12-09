package com.example.tfg.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun ValidateEmailScreen(navController: NavController) {

    var isLoading: MutableState<Boolean> = remember { mutableStateOf(true) }

    var user = Firebase.auth.currentUser
    if (user != null) {
        var email = user.email.toString()
    }
    val context = LocalContext.current

    Column (modifier = Modifier.fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {   BackButton(
        navController = navController,
        modifier = Modifier.align(Alignment.Start)
        )
        HeaderImagen(modifier=Modifier,navController)

        Row(modifier = Modifier.size(50.dp), horizontalArrangement = Arrangement.Center){

            CircularProgressIndicator(color = Color(0xFFFF5290))
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Enlace de verificación enviado,")
            Text("valide su correo")
        }


        OutlinedButton(onClick = {
                if (user != null) {
                    user?.sendEmailVerification()
                    Toast.makeText(context, "Enlace reenviado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                }
            },
            modifier= Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF5290)
            )
        ) {
            Text("Reenviar enlace", fontWeight = FontWeight.Bold, color = Color.White)
        }





//            Spacer(modifier = Modifier.height(20.dp))
//
//            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                Spacer(modifier = Modifier.height(20.dp))
//                Text(text = "Enlace de verificación enviado. Valida tu correo", color = Color.Black)
//            }
//
//            Spacer(modifier = Modifier.height(30.dp))
//
//            Button(
//                onClick = {
//                    if (user != null) {
//                        user?.sendEmailVerification()
//                        Toast.makeText(context, "Enlace reenviado", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "Usuario no registrado", Toast.LENGTH_SHORT).show()
//                    }
//                },
//                shape = RoundedCornerShape(50.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .padding(horizontal = 40.dp)
//                    .clip(RoundedCornerShape(50.dp)),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black
//                )
//            ) {
//                Text(text = "Reenviar enlace", color = Color.White)
//            }
//            Spacer(modifier = Modifier.height(15.dp))

        }
// Para usar corrutinas en un composable, usamos 'LaunchedEffect'
//        LaunchedEffect(key1 = user?.isEmailVerified) { //El parámetro key1 asegura que el efecto se recompondrá cuando el valor de user?.isEmailVerified cambie.
//            while (true) {
//                user = Firebase.auth.currentUser
//                Firebase.auth.currentUser?.reload() // Recargamos el usuario para comprobar cualquier actualización
//                if (user?.isEmailVerified == true) {
//                    Toast.makeText(context, "Iniciando sesión", Toast.LENGTH_SHORT).show()
//                    // Si el email se ha verificado, se navega a la home screen
//
//
//                } else {
//                    // Si el email no se ha verificado, lo comprobará de nuevo en 5 segundos
//                    //delay() // Espera 2.5 segundos
//                }
//            }
//        }

}

@Preview(showSystemUi = true)
@Composable
fun PCVal(){
    TFGTheme {
        ValidateEmailScreen( navController = rememberNavController())
    }
}



