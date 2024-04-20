package com.example.tfg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tfg.StartScreen
import com.example.tfg.screens.EditProfileScreen
import com.example.tfg.screens.FavsScreen
import com.example.tfg.screens.LoginScreen
import com.example.tfg.screens.MainListScreen
import com.example.tfg.screens.NewProductScreen
import com.example.tfg.screens.NewUserScreen
import com.example.tfg.screens.ProductDetailsScreen
import com.example.tfg.screens.ProfileScreen

//se encarga de gestionar la navegación entre pantallas.
@Composable
fun AppNavigation() {

    val navController= rememberNavController() //hay que propagarla por todas las pantallas.
    NavHost(navController = navController, startDestination = AppScreens.StartScreen.route ) {
        composable(route=AppScreens.StartScreen.route){
            StartScreen()
        }
        composable (route=AppScreens.LoginScreen.route){
            LoginScreen()
        }
        composable(route=AppScreens.NewUserScreen.route){
            NewUserScreen()
        }
        composable(route=AppScreens.FavsScreen.route){
            FavsScreen()
        }
        composable(route=AppScreens.ProfileScreen.route){
            ProfileScreen()
        }
        composable(route=AppScreens.EditProductScreen.route){
            EditProductScreen()
        }
        composable(route=AppScreens.MainListScreen.route){
            MainListScreen()
        }
        composable(route=AppScreens.ProductDetailsScreen.route){
            ProductDetailsScreen()
        }
        composable(route=AppScreens.NewProductScreen.route){
            NewProductScreen()
        }
        composable(route=AppScreens.EditProfileScreen.route){
            EditProfileScreen()
        }
    }


}