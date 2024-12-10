package com.example.tfg.ui.screens.add_edit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.screens.login.Login
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.NewProductVM
import kotlinx.coroutines.launch

@Composable
fun NewProductScreen(navController: NavController) {

    val vm= NewProductVM()

    //declaramos las variables que necesita la vista
    val nombre:String by vm.nombre.observeAsState(initial="")
    val marca:String by vm.marca.observeAsState(initial="")
    val nombreOG:String by vm.nombreOG.observeAsState(initial = "")
    val marcaOG:String by vm.marcaOG.observeAsState(initial = "")
    val imagen:String by vm.imagen.observeAsState(initial = "")
    val ogBool by vm.ogBool.observeAsState(initial = false)
    val goClicked:Boolean by vm.goClicked.observeAsState(initial = false)



//    var nombre = remember { mutableStateOf("") }
//    var marca= remember { mutableStateOf("") }
//    var nombreOG= remember { mutableStateOf("") }
//    var marcaOG= remember { mutableStateOf("") }
//    var imagen= remember { mutableStateOf("") }
//    var og= remember { mutableStateOf(false) }


Column(modifier= Modifier
    .fillMaxSize()
    .background(Color.White)) {

    //titulo
    Row(
        modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 20.dp)

    ) { Text("Add a new one", fontWeight = FontWeight.Bold, fontSize = 24.sp) }

    Column(
        modifier = Modifier.fillMaxSize().weight(1f). padding(10.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {

        //foto del producto nuevo
        NewProdPic(imagen) //{vm.createProduct(nombre,marca,nombreOG, marcaOG, it)}

        //ponemos un espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdName(nombre) {
            vm.createProduct(
                it,
                marca,
                nombreOG,
                marcaOG,
                ogBool,
                imagen,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdMarca(marca) {
            vm.createProduct(
                nombre,
                it,
                nombreOG,
                marcaOG,
                ogBool,
                imagen,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgName(nombreOG) {
            vm.createProduct(
                nombre,
                marca,
                it,
                marcaOG,
                ogBool,
                imagen,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgMarca(marcaOG) {
            vm.createProduct(
                nombre,
                marca,
                nombreOG,
                it,
                ogBool,
                imagen,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOg(ogBool) {
            vm.createProduct(
                nombre,
                marca,
                nombreOG,
                marcaOG,
                it,
                imagen,
                goClicked
            )
        }
        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))


        GoButton(navController, goClicked) {
            vm.createProduct(
                nombre,
                marca,
                nombreOG,
                marcaOG,
                ogBool,
                imagen,
                it
            )
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        BottomBar(navController)
    }
}

}

@Composable
fun NewProdName(nombre:String, onTextFieldChanged: (String) -> Unit) {

    TextField(
        value = nombre,
        onValueChange = {onTextFieldChanged(it)},
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
fun NewProdMarca(marca:String, onTextFieldChanged: (String) -> Unit ) {
    TextField(
        value = marca,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Marca del producto") }
    )
}

@Composable
fun NewProdOgName(nombreOG:String, onTextFieldChanged: (String) -> Unit ) {
    TextField(
        value = nombreOG,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Nombre del producto original") }
    )
}

@Composable
fun NewProdOgMarca(marcaOG:String, onTextFieldChanged: (String) -> Unit ) {
    TextField(
        value = marcaOG,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Marca del producto original") }
    )
}

@Composable
fun NewProdOg(ogBool:Boolean, onTextFieldChanged: (Boolean) -> Unit) {
    var isChecked = remember { mutableStateOf(false) }
    val ogFalse =false
    val ogTrue=true
   Row (
       modifier = Modifier.padding(10.dp),
       verticalAlignment = Alignment.CenterVertically
   ){
       Text("¿Es el original?")

       Checkbox(checked = isChecked.value,
           onCheckedChange = {isChecked.value=it
                            Log.i("checkbox","${isChecked.value}")
                             if (isChecked.value) {
                                 onTextFieldChanged(ogTrue)
                             } else {
                                 onTextFieldChanged(ogFalse)
                             } },
           colors = CheckboxDefaults.colors(
               checkedColor = Color(0xFFFF5290)
           )
       )
   }
}


//TODO: hay que hacer una función que habilite o no el boton?
@Composable
fun GoButton(
    navController: NavController,
    goClicked:Boolean,
    onTextFieldChanged: (Boolean) -> Unit
) {


    Button(onClick = { onTextFieldChanged(true)

        navController.navigate(AppScreens.MainListScreen.route)
    },

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


@Preview(showSystemUi = true)
@Composable
fun PCAddPr(){
    TFGTheme {
        NewProductScreen(navController = rememberNavController())
    }
}