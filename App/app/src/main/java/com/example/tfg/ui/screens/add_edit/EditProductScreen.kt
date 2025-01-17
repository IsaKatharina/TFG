package com.example.tfg.ui.screens.add_edit

import android.content.Context
import android.graphics.Paint.Align
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.toCoilUri
import coil3.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.DeleteVM
import com.example.tfg.viewmodels.EditProductVM
import com.example.tfg.viewmodels.ProductDetailsVM

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EditProductScreen(navController: NavController, idProduct: Int) {

    val vmGetProduct = ProductDetailsVM()
    val vmEditProduct = EditProductVM()

    val isLoading: Boolean by vmGetProduct.isLoading.observeAsState(initial = true)

    //buscamos el producto correspondiente
    val product: Product by vmGetProduct.productFound.observeAsState(initial = Product())

    //variable local del producto
    var nombre by remember { mutableStateOf(product.nombre) }
    var marca by remember { mutableStateOf(product.marca) }
    var nombreOG by remember { mutableStateOf(product.nombreOG) }
    var marcaOG by remember { mutableStateOf(product.marcaOG) }
    var ogString by remember { mutableStateOf(product.original) }
    var og:Boolean by remember { mutableStateOf<Boolean>(false) }
    var imagen by remember { mutableStateOf(product.imagen.toUri()) }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            if (uri != null) {
                imagen = uri.toCoilUri()
            }
        }
    )

    LaunchedEffect(idProduct) {
        vmGetProduct.getProduct(idProduct)
    }

    if (isLoading) {

        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color(0xFFFF5290))

        }
    } else {
        //en caso de que el idProducto sea distinto de 0
        if (product.idProduct != 0) {

            var isChecked = remember { mutableStateOf(og) }
            //hacemos una copia del producto que nos ha llegado por la api
            var productOG: Product = product

            Column (
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
            {
                Row(
                    modifier = Modifier
                ) {
                    BackButton(
                        navController = navController,
                        modifier = Modifier
                    )
                }

                Row (modifier = Modifier.align(Alignment.End)){

                    DeleteButton(navController, idProduct)
                }


                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(10.dp)
                        .verticalScroll(state = rememberScrollState(), enabled = true)
                ) {
                    //foto del producto nuevo
                    //TODO:terminar esto
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp)
                            .padding(15.dp, 0.dp, 15.dp, 0.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        if (product.idProduct == 34) {
                            Image(
                                painterResource(id = R.drawable.wha_a_tint),
                                contentDescription = "hardcoded",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else if (product.idProduct == 35) {
                            Image(
                                painterResource(id = R.drawable.benetint),
                                contentDescription = "hardcoded",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            //la imagen se cargará de forma asíncrona, viene de la api
                            AsyncImage(
                                model = imagen,
                                contentDescription = null,
                                placeholder = (painterResource(R.drawable.home_pink)),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Row (modifier = Modifier.align(Alignment.CenterHorizontally)){

                        IconButton(onClick = {
                            picker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )

                        }) {
                            Icon(
                                painterResource(id = R.drawable.camara_pink),
                                contentDescription = "camara_pink",
                                tint = Color(0xFFFF5290),
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }


                    //ponemos un espacio
                    Spacer(modifier = Modifier.padding(16.dp))

                    //nombre del producto
                    if (nombre==""){
                        nombre=product.nombre
                    }
                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 0.dp, 15.dp, 0.dp),
                        singleLine = true,
                        maxLines = 1,
                        placeholder = { Text(text = product.nombre) }
                    )

                    //otro espacio
                    Spacer(modifier = Modifier.padding(16.dp))

                    if (marca==""){
                        marca=product.marca
                    }
                    //nombre de la marca
                    TextField(
                        value = marca,
                        onValueChange = { marca = it
                                      },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 0.dp, 15.dp, 0.dp),
                        singleLine = true,
                        maxLines = 1,
                        placeholder = { Text(text = product.marca) }
                    )

                    //otro espacio
                    Spacer(modifier = Modifier.padding(16.dp))

                    if (nombreOG==""){
                        nombreOG=product.nombreOG
                    }
                    //nombbre del producto original
                    TextField(
                        value = nombreOG,
                        onValueChange = { nombreOG = it
                            },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 0.dp, 15.dp, 0.dp),
                        singleLine = true,
                        maxLines = 1,
                        enabled = !isChecked.value,
                        placeholder = { Text(text = product.nombreOG) }
                    )


                    //otro espacio
                    Spacer(modifier = Modifier.padding(16.dp))

                    if (marcaOG==""){
                        marcaOG=product.marcaOG
                    }
                    //nombre de la marca original
                    TextField(
                        value = marcaOG,
                        onValueChange = { marcaOG = it
                            },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 0.dp, 15.dp, 0.dp),
                        singleLine = true,
                        maxLines = 1,
                        enabled = !isChecked.value,
                        placeholder = { Text(text = product.marcaOG) }
                    )


                    //otro espacio
                    Spacer(modifier = Modifier.padding(16.dp))

                    //parseamos de string a bool
                    if (ogString.equals("no")) {
                        og = false

                    } else {
                        og = true

                    }
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("¿Es el original?")
                        isChecked.value=og

                        Log.i("checkbox","${isChecked.value}")

                        Checkbox(
                            checked = og,
                            onCheckedChange = { isChecked.value=it

                                //parseamos de bool a string
                                if (isChecked.value) {
                                    ogString="si"

                                } else {
                                    ogString="no"
                                }

                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFFF5290)
                            )
                        )
                    }
                }

                Button(
                    onClick = {
                        vmEditProduct.editProduct(
                            productOG,
                            nombre,
                            marca,
                            nombreOG,
                            marcaOG,
                            ogString,
                            imagen.toString()
                        )

                        navController.navigate(AppScreens.MainListScreen.route)
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 0.dp, 15.dp, 5.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5290)
                    )
                ) {
                    Text(text = "Actualizar")

                }


            }

            //ponemos un mensaje de error.
        }
        else {

            Text("No ha salido bien")
            Log.i("det", "no ha salido bien")

        }
    }

}

@Composable
fun DeleteButton(navController: NavController, idProduct: Int) {
    // Se debe manejar el estado del dialogo
    var showDialog = remember { mutableStateOf(false) }
    val deleteVM = DeleteVM()
    val context = LocalContext.current


    // El IconButton abre el dialogo
    IconButton(onClick = { showDialog.value = true }) {
        Icon(
            painter = painterResource(id = R.drawable.delete_icon),
            contentDescription = "Delete",
            tint = Color.Red,
            modifier = Modifier.size(35.dp)
        )
    }

    // Mostrar el Dialog solo si showDialog es true
    if (showDialog.value) {

        //creamos una ventana emergente
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "¿Seguro que quieres eliminar este producto?",
                        modifier = Modifier.padding(16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        TextButton(
                            onClick = { showDialog.value = false },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("No")
                        }
                        TextButton(
                            onClick = {
                                deleteVM.deleteProduct(idProduct)

                                Log.i("borrar", "le ha dado a confirmar borrar")

                                //TODO: si el producto se ha eliminado bien, pues mandamos un toast y vamos para atrás
                                navController.navigate(AppScreens.ProfileScreen.route)

                                Toast.makeText(
                                    context,
                                    "Se ha eliminado un producto",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Si")
                        }
                    }

                }
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun PCEditPr(){
    TFGTheme {
        EditProductScreen(navController = rememberNavController(), idProduct = 1)
    }
}