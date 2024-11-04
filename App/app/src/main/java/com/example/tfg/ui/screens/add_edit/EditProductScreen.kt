package com.example.tfg.ui.screens.add_edit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.tfg.core.models.Product
import com.example.tfg.core.presentation.buttons.BackButton
import com.example.tfg.navigation.AppScreens
import com.example.tfg.ui.theme.TFGTheme
import com.example.tfg.viewmodels.EditProductVM
import com.example.tfg.viewmodels.ProductDetailsVM

@Composable
fun EditProductScreen(navController: NavController, idProduct: Int) {

    val vmGetProduct = ProductDetailsVM()
    val vmEditProduct = EditProductVM()

    //TODO:poner a true
    val isLoading: Boolean by vmGetProduct.isLoading.observeAsState(initial = true)

    //buscamos el producto correspondiente
    val product: Product by vmGetProduct.productFound.observeAsState(initial = Product())
    val editClicked: Boolean by vmEditProduct.editClicked.observeAsState(initial = false)

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

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White)
            ) {
                //aquí simplemente ponemos los nombre de los composables
                BackButton(
                    navController = navController,
                    modifier = Modifier.align(Alignment.Start)
                )
                //titulo
                Row(
                    modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 20.dp)

                ) { Text("Edit this one", fontWeight = FontWeight.Bold) }

                //foto del producto nuevo
                NewProdPic(product.imagen) //{vm.createProduct(nombre,marca,nombreOG, marcaOG, it)}

                //ponemos un espacio
                Spacer(modifier = Modifier.padding(16.dp))
                EditProdName(product.nombre) {
                    vmEditProduct.editProduct(product, editClicked)
                }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                EditProdMarca(product.marca) {
                    vmEditProduct.editProduct(product, editClicked)
                }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                EditProdOgName(product.nombreOG) {
                    vmEditProduct.editProduct(product, editClicked)
                }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))
                EditProdOgMarca(product.marcaOG) {
                    vmEditProduct.editProduct(product, editClicked)
                }

                //otro espacio
                //TODO:esto hay que modificarlo
//                Spacer(modifier = Modifier.padding(16.dp))
//                EditProdOg(product.original) {
//                    vmEditProduct.editProduct(product, editClicked)
//                }

                //otro espacio
                Spacer(modifier = Modifier.padding(16.dp))


                EditProductButton(navController) {
                    vmEditProduct.editProduct(product, editClicked)
                }
            }

            //TODO:ponemos un mensaje de error.
        } else {

            Text("No ha salido bien")
            Log.i("det", "no ha salido bien")

        }
    }
}

    @Composable
    fun EditProdName(nombre: String, onTextFieldChanged: (String) -> Unit) {

        TextField(
            value = nombre,
            onValueChange = { onTextFieldChanged(it) },
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
    fun EditProdPic(imagen: String) {
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
            IconButton(onClick = { }) {
                Icon(
                    painterResource(id = R.drawable.camara_pink),
                    contentDescription = "camara_pink",
                    tint = Color(0xFFFF5290)
                )
            }
        }
    }

    @Composable
    fun EditProdMarca(marca: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = marca,
            onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "Marca del producto") }
        )
    }

    @Composable
    fun EditProdOgName(nombreOG: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = nombreOG,
            onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "Nombre del producto original") }
        )
    }

    @Composable
    fun EditProdOgMarca(marcaOG: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = marcaOG,
            onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "Marca del producto original") }
        )
    }

    @Composable
    fun EditProdOg(ogBool: Boolean, onTextFieldChanged: (Boolean) -> Unit) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("¿Es el original?")

            Checkbox(
                checked = ogBool,
                onCheckedChange = { onTextFieldChanged(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFFF5290)
                )
            )
        }
    }


    //TODO: hay que hacer una función que habilite o no el boton?
    @Composable
    fun EditProductButton(
        navController: NavController,
        onTextFieldChanged: (Boolean) -> Unit
    ) {
        Button(
            onClick = {
                onTextFieldChanged(true)
                navController.navigate(AppScreens.MainListScreen.route)
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF5290)
            )
        ) {
            Text(text = "Añadir")

        }
    }





@Preview(showSystemUi = true)
@Composable
fun PCEditPr(){
    TFGTheme {
        EditProductScreen(navController = rememberNavController(), idProduct = 1)
    }
}