package com.example.tfg.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.navigation.AppScreens
import com.example.tfg.screens.composables.HeaderImagen
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.LoginVM
import kotlinx.coroutines.launch

@Composable
fun NewUserScreen(navController: NavController) {
    Box(modifier= Modifier.fillMaxSize()
    ) {
        NewUser(Modifier.align(Alignment.Center), navController)
    }

}

@Composable
fun NewUser(modifier: Modifier, navController: NavController){

    //declaramos el vm
    val vm= LoginVM()

    //declaramos las variables que necesita la vista
    val email:String by vm.email.observeAsState(initial="")
    val password:String by vm.password.observeAsState(initial="")
    val loginEnable:Boolean by vm.loginEnable.observeAsState(initial=false)
    val isLoading:Boolean by vm.isLoading.observeAsState(initial=false)

    //aquí declaramos una corrutina.
    //TODO:ver en el vídeo pa qué
    val coroutineScope= rememberCoroutineScope()

    //en caso de que esté cargando ponemos un circulito para indicar la carga
    if (isLoading){
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else {
        Column (){
            //aquí simplemente ponemos los nombre de los composables
            // (sus funciones), no los "pintamos"
            HeaderImagen(Modifier.align(Alignment.CenterHorizontally), navController)

            //ponemos un espacio
            Spacer(modifier= Modifier.padding(16.dp))
            NewUserName(navController)

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            NewEmail(navController)

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            NewPassword(navController)

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            NewPic(navController)

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))

            //TODO:entender bien
            WelcomeButton(navController)


        }
    }

}

@Composable
fun WelcomeButton(navController: NavController) {
    Button(onClick = { },
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290)
        )) {
        Text(text = "Welcome!")

    }
}

@Composable
fun NewPic(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
        //TODO:ponerlo en el centro
    )
        {
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            ) {
                drawCircle(
                    color = Color(0xFFFF5290),
                    center = center,
                    radius = size.minDimension / 2,
                    style = Stroke(width = 2.dp.toPx()), // Ancho del borde


                )
            }

           IconButton(onClick = { /*TODO*/ }) {
               Icon(painterResource(id = R.drawable.camara_pink), contentDescription = "camara_pink")
           }
    }

}




@Composable
fun NewPassword(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(text = "Password") }
    )
}

@Composable
fun NewEmail(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Email") }
    )
}

@Composable
fun NewUserName(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Username") }
    )
}


