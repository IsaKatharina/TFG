package com.example.tfg.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tfg.navigation.AppScreens
import com.example.tfg.screens.composables.HeaderImagen
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.LoginVM
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(modifier: Modifier, navController: NavController) {

    Box(modifier= Modifier.fillMaxSize()
    ) {
     Login(Modifier.align(Alignment.Center), navController)
    }

}

@Composable
fun Login( modifier: Modifier, navController: NavController){

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
        Column(modifier = modifier) {
            //aquí simplemente ponemos los nombre de los composables
            // (sus funciones), no los "pintamos"
            HeaderImagen(Modifier.align(Alignment.CenterHorizontally), navController)

            //ponemos un espacio
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { vm.onLoginChanged(it, password) }

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { vm.onLoginChanged(email, it) }

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.End))

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))

            //TODO:entender bien
            LoginButton(/*loginEnable*/navController)
                /*coroutineScope.launch {
                    vm.onLoginSelected()*/



        }
    }

}

@Composable
fun LoginButton(/*loginEnable: Boolean, onLoginSelected:()-> Unit*/ navController: NavController) {
    Button(onClick = { /*onLoginSelected()*/navController.navigate(AppScreens.MainListScreen.route) },
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp,0.dp, 15.dp, 0.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290),
            disabledContainerColor = Color.White
        )
    ,/* enabled = loginEnable*/) {
        Text(text = "Login")
        
    }

}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        modifier=modifier.clickable { }, //TODO: te llevaría a una pestaña de recuperación
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFF5290)
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp,0.dp, 15.dp, 0.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        //TODO:Falta el onTextFieldChange()
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Password")}
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
   TextField(
       value = email,
       onValueChange = {},
       modifier = Modifier.fillMaxWidth()
           .padding(15.dp,0.dp, 15.dp, 0.dp),
       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
       //TODO:Falta el onTextFieldChange()
       singleLine = true,
       maxLines = 1,
       placeholder = { Text(text = "Email")}

       )

}

