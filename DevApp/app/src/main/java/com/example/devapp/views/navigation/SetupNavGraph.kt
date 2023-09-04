package com.example.devapp.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.devapp.views.screens.HomeScreen
import com.example.devapp.views.screens.LoginScreen
import com.example.devapp.views.screens.SplashScreen
import com.example.devapp.views.viewModel.MainViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: MainViewModel){
    NavHost(navController = navController, startDestination= Route.Splash.route){
        composable(route= Route.Splash.route){
            SplashScreen(navController=navController){

            }
        }

        composable(route= Route.Login.route){
            LoginScreen(navHostController = navController, viewModel = viewModel)
        }

        composable(route= Route.Home.route){
            HomeScreen(navController = navController, viewModel = viewModel)
        }
    }
}