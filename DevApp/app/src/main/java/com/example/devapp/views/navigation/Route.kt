package com.example.devapp.views.navigation

sealed class Route(val route: String){
    object Splash: Route("splash_screen")
    object Login: Route("login_screen")
    object Home: Route("home_screen")
}
