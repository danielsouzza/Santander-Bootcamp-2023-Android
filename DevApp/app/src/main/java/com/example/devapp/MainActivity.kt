package com.example.devapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.devapp.ui.theme.DevAppTheme
import com.example.devapp.views.screens.RepositoriesScreen
import com.example.devapp.views.screens.UserScreen
import com.example.devapp.views.viewModel.MainViewModel


private const val CHOOSE_USER = "choose_user"
private const val VIEW_PROJECTS = "view_projects"


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val mainNavController = rememberNavController()
                    val mainViewModel = remember {
                        mutableStateOf(MainViewModel(mainNavController))
                    }

                    NavHost(navController = mainNavController, startDestination= CHOOSE_USER){
                        composable(route= CHOOSE_USER){
                            UserScreen(navHostController = mainNavController, viewModel = mainViewModel.value)
                        }

                        composable(route= VIEW_PROJECTS){
                            RepositoriesScreen(navController = mainNavController, viewModel = mainViewModel.value)
                        }
                    }
                }
            }
        }
    }
}
