package com.example.tfg.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfg.screens.EditProductScreen
import com.example.tfg.screens.EditProfileScreen
import com.example.tfg.screens.FavsScreen
import com.example.tfg.screens.LoginScreen
import com.example.tfg.screens.MainListScreen
import com.example.tfg.screens.NewProductScreen
import com.example.tfg.screens.NewUserScreen
import com.example.tfg.screens.ProductDetailsScreen
import com.example.tfg.screens.ProfileScreen
import com.example.tfg.screens.StartScreen

//se encarga de gestionar la navegación entre pantallas.
@Composable
fun AppNavigation() {

    val navController= rememberNavController() //hay que propagarla por todas las pantallas.
    NavHost(navController = navController, startDestination = AppScreens.StartScreen.route ) {
        composable(route=AppScreens.StartScreen.route){
            StartScreen(modifier=Modifier, navController)
        }
        composable (route=AppScreens.LoginScreen.route){
            LoginScreen(modifier=Modifier, navController)
        }
        composable(route=AppScreens.NewUserScreen.route){
            NewUserScreen(navController)
        }

        composable(route=AppScreens.FavsScreen.route){
            FavsScreen(navController)
        }
        composable(route=AppScreens.ProfileScreen.route){
            ProfileScreen(navController)
        }
        composable(route=AppScreens.EditProductScreen.route){
            EditProductScreen(navController)
        }
        composable(route=AppScreens.MainListScreen.route){
            MainListScreen(navController)
        }
        composable(route=AppScreens.ProductDetailsScreen.route){
            ProductDetailsScreen(navController)
        }
        composable(route=AppScreens.NewProductScreen.route){
            NewProductScreen(navController )
        }
        composable(route=AppScreens.EditProfileScreen.route){
            EditProfileScreen(navController)
        }
    }


}