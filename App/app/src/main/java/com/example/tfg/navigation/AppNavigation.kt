package com.example.tfg.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tfg.ui.screens.profile.EditProfileScreen
import com.example.tfg.ui.screens.list.MainListScreen
import com.example.tfg.ui.screens.login.LoginScreen
import com.example.tfg.ui.screens.login.NewUserScreen
import com.example.tfg.ui.screens.login.StartScreen
import com.example.tfg.ui.screens.list.ProductDetailsScreen
import com.example.tfg.ui.screens.profile.ProfileScreen
import com.example.tfg.viewmodels.LoginVM
import com.example.tfg.ui.screens.add_edit.EditProductScreen
import com.example.tfg.ui.screens.list.FavsScreen
import com.example.tfg.ui.screens.add_edit.NewProductScreen

//se encarga de gestionar la navegación entre pantallas.
@Composable
fun AppNavigation() {

    val vm= LoginVM() //instanciamos el vm del login
    val navController= rememberNavController() //hay que propagarla por todas las pantallas.
    NavHost(navController = navController, startDestination = AppScreens.StartScreen.route ) {
        composable(route=AppScreens.StartScreen.route){
            StartScreen(modifier=Modifier, navController)
        }
        composable (route=AppScreens.LoginScreen.route){
            LoginScreen(modifier=Modifier, navController, vm )
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
        composable(route=AppScreens.ProductDetailsScreen.route + "/{idProduct}",
            arguments = listOf(
                //tenemos que pasar como navArgument lo que identifica a cada producto.
                navArgument("idProduct"){
                    type= NavType.IntType
                })
        ) {
            it.arguments?.let { it1 -> ProductDetailsScreen(navController, it1.getInt("idProduct")) }
        }

        composable(route=AppScreens.NewProductScreen.route){
            NewProductScreen(navController )
        }
        composable(route=AppScreens.EditProfileScreen.route){
            EditProfileScreen(navController)
        }
    }


}


