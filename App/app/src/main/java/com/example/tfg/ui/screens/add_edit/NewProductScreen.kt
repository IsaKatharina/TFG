package com.example.tfg.ui.screens.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.HeaderImagen
import com.example.tfg.core.presentation.composables.ProfileCircle
import com.example.tfg.ui.screens.login.LoginScreen
import com.example.tfg.ui.screens.login.NewEmail
import com.example.tfg.ui.screens.login.NewPassword
import com.example.tfg.ui.screens.login.NewPic
import com.example.tfg.ui.screens.login.NewUserName
import com.example.tfg.ui.screens.login.WelcomeButton
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.LoginVM

@Composable
fun NewProductScreen(navController: NavController) {

    Column (modifier= Modifier.fillMaxSize()
        .background(Color.White)
    ) {
        //aquí simplemente ponemos los nombre de los composables
        BackButton(
            navController = navController,
            modifier = Modifier.align(Alignment.Start)
        )
        //titulo
        Row (
            modifier = Modifier.padding(10.dp,20.dp, 0.dp, 20.dp)

        ) { Text("Add a new one", fontWeight = FontWeight.Bold) }

        //foto del producto nuevo
        NewProdPic(navController)

        //ponemos un espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdName(navController)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdMarca(navController)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgName(navController)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgMarca(navController)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOg(navController)



        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))

        //TODO:entender bien
        WelcomeButton(navController)
    }


}

@Composable
fun NewProdName(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Nombre del producto") }
    )
}

@Composable
fun NewProdPic(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()
        .height(200.dp)
        .padding(15.dp,0.dp,15.dp,0.dp)
        .border(
            width = 1.dp,
            color = Color.Gray
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

        //TODO:ponerlo en el centro
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.camara_pink),
                contentDescription = "camara_pink",
                tint = Color(0xFFFF5290)
            )
        }
    }
}

@Composable
fun NewProdMarca(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Marca del producto") }
    )
}

@Composable
fun NewProdOgName(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Nombre del producto original") }
    )
}

@Composable
fun NewProdOgMarca(navController: NavController) {
    TextField(
        value = "", //TODO:va para vm
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Marca del producto original") }
    )
}

@Composable
fun NewProdOg(navController: NavController) {
   Row (
       modifier = Modifier,
       horizontalArrangement = Arrangement.Center
   ){
       Text("¿Es el original?")

       Checkbox(checked = false,
           onCheckedChange = trueOG()
       )
   }
}

fun trueOG(): ((Boolean) -> Unit)? {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun PCAddPr(){
    TFGTheme {
        NewProductScreen(navController = rememberNavController())
    }
}