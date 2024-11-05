package com.example.tfg.core.presentation.buttons

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.tv.material3.IconButton
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tfg.R

@Composable
fun DeleteButton(idProduct: Int, imagen: String,navController: NavController) {
    // Se debe manejar el estado del dialogo
    var showDialog = remember { mutableStateOf(false) }

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
        borraProducto(
            imagen = imagen,
            onDismiss = { showDialog.value = false }, // Para cerrar el diálogo
            onConfirm = { confirmarBorrado(idProduct, navController) } // Llamar a la API de borrado
        )
    }
}


@Composable
fun borraProducto(imagen: String, onDismiss:()->Unit, onConfirm:()->Unit) {

    //creamos una ventana emergente
    Dialog(onDismissRequest = {}) {
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
                AsyncImage(
                    model= ImageRequest.Builder(LocalContext.current)
                        .data(imagen)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.home_pink),
                    contentDescription = stringResource(id= R.string.app_name),
                    modifier = Modifier.fillMaxSize()
                )

                Text("¿Seguro que quieres eliminar este producto?",
                    modifier = Modifier.padding(16.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                        TextButton(
                            onClick = {onDismiss},
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("No")
                        }
                    TextButton(
                        onClick = {onConfirm},
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Si")
                    }
                }

            }
        }
    }
}

//llama a la api y toda la pesca
fun confirmarBorrado(idProduct: Int, navController: NavController) {

    Log.i("bor", "le ha dado a confirmar borrar")

    //TODO: si el producto se ha eliminado bien, pues mandamos un toast y vamos para atrás
    navController.popBackStack()

}


