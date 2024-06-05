package com.example.beta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.beta.ui.screens.MainProductsList
import com.example.beta.ui.theme.BetaTheme
import com.example.beta.ui.vm.MainProductsListVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BetaTheme {
                Surface (
                    modifier=Modifier.fillMaxSize()
                ){
                    MainProductsList()
                }
            }
        }
    }
}

