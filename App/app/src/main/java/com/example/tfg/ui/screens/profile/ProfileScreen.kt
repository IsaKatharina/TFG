package com.example.tfg.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.models.User
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.core.presentation.composables.ProductsGrid
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.screens.list.MainListScreen
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.ProfileVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

@Composable
fun ProfileScreen(navController: NavController) {

    val vm=ProfileVM()
    val userFound:User by vm.userFound.observeAsState(initial = User())
    //creamos una lambda para navegar a editar un producto
    val navigateToEdit:(Int) ->Unit= {idProduct ->
        navController.navigate(AppScreens.EditProductScreen.route+"/$idProduct")

        Log.i("nav", "navegando a la página editar el producto $idProduct")
    }

    //Llamamos a una instancia de Firebase para que nos devuelva el correo del usuario
    var userEmail= FirebaseAuth.getInstance().currentUser?.email

    //Llamamos a la api para que nos traiga los datos del usuario en cuestión.
    LaunchedEffect(userEmail) {
        if (userEmail != null) {
            vm.getUser(userEmail)
        }
    }

    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {

        //aqui va la top bar
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 1.dp,
                color = Color.DarkGray
            ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(modifier = Modifier.size(50.dp)) {
                ProfileCircle(navController, userFound.picture)
            }


            Column (
                modifier = Modifier.padding(10.dp)
            ){
                Text(userFound.nombreUsu, fontWeight = FontWeight.Bold)
                Log.i("user", userFound.nombreUsu)

                Text(userFound.correo, fontWeight = FontWeight.Thin)
                Log.i("user", userFound.correo)
            }

            //botón de logout
           IconButton(
               onClick = {
                   if (userEmail != null) {
                       logOut(navController, userEmail)
                   }
               },
           ){
               Icon(
                   painter= painterResource(id = R.drawable.exit_icon),
                   contentDescription = "EditProfile",
                   tint = Color(0xFFFF5290),
                   modifier= Modifier.size(35.dp)
               )

           }

        }

        Row (
            modifier = Modifier.padding(10.dp,20.dp, 0.dp, 20.dp)

        ) { Text("Your products", fontWeight = FontWeight.Bold) }

        Column (modifier= Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(15.dp)
        ) {
            ProductsGrid(modifier = Modifier, userFound.idUsuario, onEditProductClick=navigateToEdit)
        }

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBar(navController)
        }
    }
}

fun logOut(navController: NavController, userEmail:String) {

    if (userEmail!=null) {
        Log.i("user", "$userEmail")
        FirebaseAuth.getInstance().signOut()
        navController.navigate(AppScreens.StartScreen.route){
            popUpTo("start_screen") {inclusive=true}
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PRProfileScreen(){
    TFGTheme {
        ProfileScreen(
            navController = rememberNavController()
        )
    }

}