package com.example.tfg.navigation

import com.example.tfg.navigation.AppScreens.LoginScreen.route


sealed class AppScreens (val route:String) {

    object LoginScreen: AppScreens("login_screen")
    object NewUserScreen: AppScreens("newuser_screen")
    object StartScreen: AppScreens("start_screen")
    /*
    object EditProductScreen:AppScreens(route:"editproduct_screen")
    object EditProfileScreen:AppScreens(route:"editprofile_screen")
    object FavsScreen:AppScreens(route:"favs_screen")
    object MainListScreen:AppScreens(route:"mainlist_screen")
    object NewProductScreen:AppScreens(route:"newproduct_screen")
    object ProductDetailsScreen:AppScreens(route:"productdetails_screen")
    object ProfileScreen:AppScreens(route:"profile_screen")*/

}