package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.tfg.R
import com.example.tfg.core.presentation.buttons.HeartButton
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProductCard(modifier: Modifier){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 100.dp, height = 100.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

          Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"",
                color = Color.Black)

            Box(modifier = Modifier.size(30.dp)
                .align(Alignment.TopEnd)
                .zIndex(1f)
                .padding(4.dp)
            ) {
                Icon(
                    modifier=Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.favs_pink),
                    contentDescription = "heart_black",
                    tint = Color(0xFFFF5290)
                )

            }
        }
    }
}



@Preview
@Composable
fun PCPre(){
    TFGTheme {
        ProductCard(modifier = Modifier)
    }
}

