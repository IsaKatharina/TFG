package com.example.tfg.ui.screens.add_edit

import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.Uri
import coil3.compose.AsyncImage
import coil3.toCoilUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tfg.R
import com.example.tfg.core.models.User
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.core.presentation.composables.BottomBar
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.screens.login.Login
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.NewProductVM
import com.example.tfg.viewmodels.ProfileVM
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun NewProductScreen(navController: NavController) {

    val vm= NewProductVM()

    //declaramos las variables que necesita la vista
    val nombre:String by vm.nombre.observeAsState(initial="")
    val marca:String by vm.marca.observeAsState(initial="")
    val nombreOG:String by vm.nombreOG.observeAsState(initial = "")
    val marcaOG:String by vm.marcaOG.observeAsState(initial = "")
    val imagen:String by vm.imagenString.observeAsState(initial = "")
    val ogBool by vm.ogBool.observeAsState(initial = false)
    val goClicked:Boolean by vm.goClicked.observeAsState(initial = false)

    var picUri: Uri? by remember { mutableStateOf<Uri?>(null) }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            if (uri != null) {
                picUri = uri.toCoilUri()
            }
        }
    )

    val vmUser= ProfileVM()
    val userFound: User by vmUser.userFound.observeAsState(initial = User())

    var isChecked = remember { mutableStateOf(false) }

    //Llamamos a una instancia de Firebase para que nos devuelva el correo del usuario
    var userEmail= FirebaseAuth.getInstance().currentUser?.email

    //Llamamos a la api para que nos traiga los datos del usuario en cuestión.
    LaunchedEffect(userEmail) {
        if (userEmail != null) {
            vmUser.getUser(userEmail)
        }
    }



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
        NewProdPic(picUri, picker)

        //ponemos un espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdName(nombre) {
            vm.createProduct(
                userFound,
                it,
                marca,
                nombreOG,
                marcaOG,
                ogBool,
                picUri,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdMarca(marca) {
            vm.createProduct(
                userFound,
                nombre,
                it,
                nombreOG,
                marcaOG,
                ogBool,
                picUri,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgName(isChecked, nombreOG) {
            vm.createProduct(
                userFound,
                nombre,
                marca,
                it,
                marcaOG,
                ogBool,
                picUri,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOgMarca(isChecked, marcaOG) {
            vm.createProduct(
                userFound,
                nombre,
                marca,
                nombreOG,
                it,
                ogBool,
                picUri,
                goClicked
            )
        }

        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))
        NewProdOg(ogBool, isChecked) {
            vm.createProduct(
                userFound,
                nombre,
                marca,
                nombreOG,
                marcaOG,
                it,
                picUri,
                goClicked
            )
        }
        //otro espacio
        Spacer(modifier = Modifier.padding(16.dp))

        GoButton(navController, goClicked) {
            vm.createProduct(
                userFound,
                nombre,
                marca,
                nombreOG,
                marcaOG,
                ogBool,
                picUri,
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewProdPic(picUri: Uri?, picker: ManagedActivityResultLauncher<PickVisualMediaRequest, android.net.Uri?>) {
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
        if (picUri == null) {
            IconButton(onClick = {
                picker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(
                    painterResource(id = R.drawable.camara_pink),
                    contentDescription = "camara_pink",
                    tint = Color(0xFFFF5290)
                )
            }

        } else {

            GlideImage(
                model = picUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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
fun NewProdOgName(isChecked: MutableState<Boolean>,nombreOG:String, onTextFieldChanged: (String) -> Unit ) {
    TextField(
        value = nombreOG,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        enabled = !isChecked.value,
        placeholder = { Text(text = "Nombre del producto original") }
    )
}

@Composable
fun NewProdOgMarca(isChecked: MutableState<Boolean>,marcaOG:String, onTextFieldChanged: (String) -> Unit ) {
    TextField(
        value = marcaOG,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp, 15.dp, 0.dp),
        singleLine = true,
        maxLines = 1,
        enabled = !isChecked.value,
        placeholder = { Text(text = "Marca del producto original") }
    )
}

@Composable
fun NewProdOg(ogBoolean: Boolean, isChecked:MutableState<Boolean>, onTextFieldChanged: (Boolean) -> Unit) {

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