package com.example.tfg.core.presentation.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(){

    var textSearch = remember { mutableStateOf("") }

    /*
    SearchBar(query = , onQueryChange = , onSearch = , active = , onActiveChange = ) {

    }*/

}
