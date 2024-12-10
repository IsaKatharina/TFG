package com.example.tfg.ui.screens.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.dal.connectivity.ConnectivityObserver
import com.example.tfg.dal.connectivity.NetworkConnectivityObserver
import com.example.tfg.navigation.AppScreens
import com.example.tfg.viewmodels.LoginVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(modifier: Modifier, navController: NavController) {

    var auth:FirebaseAuth=Firebase.auth
    val vm= LoginVM()

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
    val context= LocalContext.current
    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

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

            //aquí simplemente ponemos los nombre de los composables
            BackButton(
                navController = navController,
                modifier = Modifier.align(Alignment.Start)
            )
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

        Column(modifier = modifier.fillMaxSize()) {
            //aquí simplemente ponemos los nombre de los composables
            // (sus funciones), no los "pintamos"
            BackButton(
                navController = navController,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.padding(40.dp))

            HeaderImagen(Modifier.align(Alignment.CenterHorizontally), navController)

            //ponemos un espacio
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { vm.onLoginChanged(it, password) }

            //otro espacio
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { vm.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(modifier=Modifier.align(Alignment.End), navController)
            Spacer(modifier = Modifier.padding(35.dp))
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
    Button(
        onClick = {
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
fun ForgotPassword(modifier: Modifier, navController: NavController) {
    Button( onClick = {
            navController.navigate(AppScreens.ForgotPasswordScreen.route)
        }, modifier= Modifier
        .padding(15.dp, 0.dp, 15.dp, 0.dp)
        .height(48.dp)
        .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290),
            disabledContainerColor = Color(0xFFE2E1E5)
        )) {

        Text("Forgot Password ?")

        }


}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    //TODO:hacer lo del ojito para que se vea la contraseña
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


