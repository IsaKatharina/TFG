package com.example.tfg.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tfg.ui.screens.list.MainListScreen
import com.example.tfg.ui.screens.login.LoginScreen
import com.example.tfg.ui.screens.login.NewUserScreen
import com.example.tfg.ui.screens.login.StartScreen
import com.example.tfg.ui.screens.list.ProductDetailsScreen
import com.example.tfg.ui.screens.profile.ProfileScreen
import com.example.tfg.viewmodels.LoginVM
import com.example.tfg.ui.screens.add_edit.EditProductScreen
import com.example.tfg.ui.screens.add_edit.NewProductScreen
import com.example.tfg.ui.screens.login.ForgotPassword
import com.example.tfg.ui.screens.login.ForgotPasswordScreen
import com.google.firebase.auth.FirebaseAuth

//se encarga de gestionar la navegación entre pantallas.
@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppScreens.StartScreen.route ) {
        composable(route = AppScreens.StartScreen.route) {
            StartScreen(modifier = Modifier, navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(modifier = Modifier, navController)
        }
        composable(route = AppScreens.NewUserScreen.route) {
            NewUserScreen(navController)
        }
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(
            route = AppScreens.EditProductScreen.route + "/{idProduct}",
            arguments = listOf(
                navArgument("idProduct") {
                    type = NavType.IntType
                })
        ) {
            it.arguments?.let { it1 -> EditProductScreen(navController, it1.getInt("idProduct")) }
        }
        composable(route = AppScreens.MainListScreen.route) {
            MainListScreen(navController)
        }
        composable(
            route = AppScreens.ProductDetailsScreen.route + "/{idProduct}",
            arguments = listOf(
                //tenemos que pasar como navArgument lo que identifica a cada producto.
                navArgument("idProduct") {
                    type = NavType.IntType
                })
        ) {
            it.arguments?.let { it1 ->
                ProductDetailsScreen(
                    navController,
                    it1.getInt("idProduct")
                )
            }
        }

        composable(route = AppScreens.NewProductScreen.route) {
            NewProductScreen(navController)
        }
        composable(route = AppScreens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController)
        }
    }
}


