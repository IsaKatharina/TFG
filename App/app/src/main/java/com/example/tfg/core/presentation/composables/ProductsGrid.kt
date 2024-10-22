package com.example.tfg.core.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tfg.R
import com.example.tfg.core.models.Product
import com.example.tfg.ui.theme.TFGTheme

@Composable
fun ProductsGrid (modifier: Modifier, navController: NavController) {

    val products:List<Product> = listOf(

        Product("producto1", R.drawable.soft_glam_filter),
        Product("producto2", R.drawable.soft_glam_filter),
        Product("producto3", R.drawable.soft_glam_filter),
        Product("producto4", R.drawable.soft_glam_filter),
        Product("producto5", R.drawable.soft_glam_filter),
        Product("producto6", R.drawable.soft_glam_filter),
        Product("producto7", R.drawable.soft_glam_filter)
        
    )

    LazyVerticalStaggeredGrid (
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        content = {
            items(products) { product ->

                ProductCard(modifier, navController, product)

            }
        }
    )

}

@Preview
@Composable
fun ProductsPR(){
    TFGTheme {
        ProductsGrid(
            modifier =Modifier, navController = rememberNavController())

    }
}
