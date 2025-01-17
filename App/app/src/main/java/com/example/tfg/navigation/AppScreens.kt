package com.example.tfg.navigation

import com.example.tfg.navigation.AppScreens.LoginScreen.route

sealed class AppScreens (val route:String) {

    object LoginScreen: AppScreens("login_screen")
    object NewUserScreen: AppScreens("newuser_screen")
    object StartScreen: AppScreens("start_screen")
    object EditProductScreen:AppScreens("editproduct_screen")
    object EditProfileScreen:AppScreens("editprofile_screen")
    object FavsScreen:AppScreens("favs_screen")
    object MainListScreen:AppScreens("mainlist_screen")
    object NewProductScreen:AppScreens("newproduct_screen")
  //TODO:arreglar la navegacion
    object ProductDetailsScreen:AppScreens("productdetails_screen") {
//        fun createRoute(idProduct:Int)= "productdetails_screen/$idProduct"
    }
    object ProfileScreen:AppScreens("profile_screen")
    object ValidateEmailScreen:AppScreens("validate_email_screen")
    object ForgotPasswordScreen:AppScreens("forgot_password_screen")

}