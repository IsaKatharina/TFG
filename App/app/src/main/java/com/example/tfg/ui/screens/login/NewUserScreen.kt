package com.example.tfg.ui.screens.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.dal.connectivity.ConnectivityObserver
import com.example.tfg.dal.connectivity.NetworkConnectivityObserver
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.NewUserVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun NewUserScreen(navController: NavController) {

    val context= LocalContext.current
    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe()
        .collectAsState(initial = ConnectivityObserver.Status.Available)

    //chequea si hay conexión, si no hay, no se puede seguir con la app.
    if (status.name != "Available") {

        Log.i("sos", "$status.name")

        Column {

            //aquí simplemente ponemos los nombre de los composables
            BackButton(
                navController = navController,
                modifier = Modifier.align(Alignment.Start)
            )
            Box(
                modifier = Modifier.fillMaxSize(),
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

    } else {

    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        //declaramos el vm
        val vm = NewUserVM()

        //declaramos las variables que necesita la vista
        val email: String by vm.email.observeAsState(initial = "")
        val password: String by vm.password.observeAsState(initial = "")
        val nombreUsu: String by vm.nombreUsu.observeAsState(initial = "")
        val picture: String by vm.pic.observeAsState("")

        val newUserEnable: Boolean by vm.newUserEnable.observeAsState(initial = false)
        val welcomeClicked: Boolean by vm.welcomeClicked.observeAsState(initial = false)

        var auth: FirebaseAuth = Firebase.auth

            //TODO:comprobar que el scroll funciona
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                //aquí simplemente ponemos los nombre de los composables
                BackButton(
                    navController = navController,
                    modifier = Modifier.align(Alignment.Start)
                )
                // (sus funciones), no los "pintamos"
                HeaderImagen(Modifier.align(Alignment.CenterHorizontally), navController)

                //ponemos un espacio
                Spacer(modifier = Modifier.padding(16.dp))
                NewUserName(nombreUsu) { vm.onNewUserChanged(email, it, picture, password) }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                NewEmail(email) { vm.onNewUserChanged(it, nombreUsu, picture, password) }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                NewPassword(password) { vm.onNewUserChanged(email, nombreUsu, picture, it) }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                NewPic(navController, picture)

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))

                //TODO:entender bien
                WelcomeButton(
                    navController,
                    newUserEnable,
                    vm,
                    email,
                    nombreUsu,
                    password,
                    picture,
                    auth
                )


            }
        }
    }

}

@Composable
fun WelcomeButton(
    navController: NavController,
    newUserEnable: Boolean,
    vm: NewUserVM,
    email: String,
    nombreUsu: String,
    password:String,
    picture: String,
    auth: FirebaseAuth
) {

    var context: Context = LocalContext.current

    Button(onClick = {

        //se crea el usuario en Firebase
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                //se crea el usuario en la badat
                vm.createUser(email, nombreUsu, picture)


                navController.navigate(AppScreens.ValidateEmailScreen.route)

            } else if (task.exception is FirebaseAuthUserCollisionException) {
            Toast.makeText(context, "Ese correo ya está en uso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error creando la cuenta: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }

    },
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(48.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290)
        )
        , enabled = newUserEnable) {
        Text("Welcome")
    }
}

//TODO:terminar esto
@Composable
fun NewPic(navController:NavController,picture:String) {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(10.dp,0.dp,0.dp,0.dp),
        horizontalArrangement = Arrangement.Center

        //TODO:ponerlo en el centro
    ) {
       ProfileCircle(navController)

        IconButton(onClick = {  }) {
            Icon(painterResource(id = R.drawable.camara_pink),
                contentDescription = "camara_pink",
                tint = Color(0xFFFF5290)
            )
        }
    }
}

@Composable
fun NewPassword(password:String, onTextFieldChanged:(String)->Unit) {
    TextField(
        value = password,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        placeholder = { Text(text = "Password") }
    )
}

@Composable
fun NewEmail(email: String, onTextFieldChanged:(String)->Unit) {
    TextField(
        value = email, //TODO:va para vm
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Email") }
    )
}

@Composable
fun NewUserName(nombreUsu: String, onTextFieldChanged:(String)->Unit) {
    TextField(
        value = nombreUsu, //va para vm
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Username") }
    )
}

//@Preview
//@Composable
//fun PCNewUser(){
//    TFGTheme {
//        NewUserScreen(navController = rememberNavController())
//    }
//}


