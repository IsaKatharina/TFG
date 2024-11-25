package com.example.tfg.ui.screens.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.navigation.AppScreens
import com.example.tfg.viewmodels.LoginVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(modifier: Modifier, navController: NavController, vm: LoginVM) {

    var auth:FirebaseAuth=Firebase.auth

    Box(modifier= Modifier.fillMaxSize()
        .background(Color.White)
    ) {
     Login(Modifier.align(Alignment.Center), navController, vm, auth)
    }

}

@Composable
fun Login(modifier: Modifier, navController: NavController, vm: LoginVM, auth: FirebaseAuth){

    //declaramos las variables que necesita la vista
    val email:String by vm.email.observeAsState(initial="")
    val password:String by vm.password.observeAsState(initial="")
    val loginEnable:Boolean by vm.loginEnable.observeAsState(initial=false)
    val isLoading:Boolean by vm.isLoading.observeAsState(initial=false)
    val loginTrue:Boolean by vm.loginTrue.observeAsState(initial = false)

    //en caso de que esté cargando ponemos un circulito para indicar la carga
    if (isLoading){
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color(0xFFFF5290))
        }
    }else {

        Column(modifier = modifier.fillMaxSize()) {
            //aquí simplemente ponemos los nombre de los composables
            // (sus funciones), no los "pintamos"
            BackButton(navController = navController,
                modifier=Modifier.align(Alignment.Start))
            
            Spacer(modifier = Modifier.padding(40.dp))

            HeaderImagen(Modifier.align(Alignment.CenterHorizontally), navController)

            //ponemos un espacio
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { vm.onLoginChanged(it, password) }

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { vm.onLoginChanged(email, it) }

            ForgotPassword(Modifier.align(Alignment.End).padding(0.dp,60.dp,10.dp,16.dp))

            LoginButton(loginEnable, navController, auth, email, password)
        }
    }

}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    navController: NavController,
    auth: FirebaseAuth,
    email: String,
    password: String,

    ) {

    var context:Context= LocalContext.current
    Button(onClick = {
       auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task->
           if (task.isSuccessful) {
               navController.navigate(AppScreens.MainListScreen.route)

               //TODO: aquí hay que hacer una llamada a la api del usuario elegido y generar un usuario
           } else {
               Log.i("isabel", "error")
               //error

               Toast.makeText(context, "Este usuario no está registrado", Toast.LENGTH_LONG).show()
           }

       }
                     },
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290),
            disabledContainerColor = Color(0xFFE2E1E5)
        )
    , enabled = loginEnable) {
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
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Password")}
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        //TODO:Falta el onTextFieldChange()
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Email") },

    )
}

//@Preview
//@Composable
//fun PCLogin(){
//    TFGTheme {
//        LoginScreen(
//            modifier = Modifier,
//            navController = rememberNavController(),
//            vm = LoginVM(),
//
//        )
//    }
//}


