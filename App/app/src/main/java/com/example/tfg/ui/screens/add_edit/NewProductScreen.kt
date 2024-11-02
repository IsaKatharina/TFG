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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.NewProductVM

@Composable
fun NewProductScreen(navController: NavController) {

    val vm= NewProductVM()

    //declaramos las variables que necesita la vista
    val nombre:String by vm.nombre.observeAsState(initial="")
    val marca:String by vm.marca.observeAsState(initial="")
    val nombreOG:String by vm.nombreOG.observeAsState(initial = "")
    val marcaOG:String by vm.marcaOG.observeAsState(initial = "")
    val imagen:String by vm.imagen.observeAsState(initial = "")


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
        NewProdPic(imagen)

        //ponemos un espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdName(nombre)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdMarca(marca)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgName(nombreOG)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgMarca(marcaOG)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOg(vm)

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))


        GoButton(navController)
    }


}

@Composable
fun NewProdName(nombre:String) {
    TextField(
        value = nombre,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Nombre del producto") }
    )
}

//TODO:terminar esta parte
@Composable
fun NewProdPic(imagen: String) {
    Row(modifier = Modifier.fillMaxWidth()
        .height(200.dp)
        .padding(15.dp,0.dp,15.dp,0.dp)
        .border(
            width = 1.dp,
            color = Color.Gray
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically


    ) {
        IconButton(onClick = {  }) {
            Icon(
                painterResource(id = R.drawable.camara_pink),
                contentDescription = "camara_pink",
                tint = Color(0xFFFF5290)
            )
        }
    }
}

@Composable
fun NewProdMarca(marca:String) {
    TextField(
        value = marca,
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
fun NewProdOgName(nombreOG:String) {
    TextField(
        value = nombreOG,
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
fun NewProdOgMarca(marcaOG:String) {
    TextField(
        value = marcaOG,
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
fun NewProdOg(vm: NewProductVM) {
   Row (
       modifier = Modifier.padding(10.dp),
       horizontalArrangement = Arrangement.Center
   ){
       Text("¿Es el original?")

       Checkbox(checked = false,
           onCheckedChange = {
               //llamamos a la función que checkea el valor del original
               vm.checkOG()
           }
       )
   }
}

//TODO: hay que hacer una función que habilite o no el boton?
@Composable
fun GoButton(navController: NavController) {
    Button(onClick = {terminarAdd()},
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF5290)
        )) {
        Text(text = "Añadir")

    }
}

//fun trueOG(): ((Boolean) -> Unit)?

fun terminarAdd() {

}

@Preview
@Composable
fun PCAddPr(){
    TFGTheme {
        NewProductScreen(navController = rememberNavController())
    }
}