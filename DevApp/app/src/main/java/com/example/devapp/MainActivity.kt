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
import androidx.navigation.compose.rememberNavController
import com.example.devapp.data.PreferenceDataStore
import com.example.devapp.data.dataStore
import com.example.devapp.ui.theme.DevAppTheme
import com.example.devapp.views.navigation.SetupNavGraph
import com.example.devapp.views.viewModel.MainViewModel





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore =

        setContent {
            DevAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val navController = rememberNavController()
                    val viewModel = remember {
                        mutableStateOf(
                            MainViewModel(navHostController = navController, dataStore= PreferenceDataStore(this)){ intent->
                                startActivity(intent)
                            }
                        )
                    }
                    SetupNavGraph(navController = navController, viewModel = viewModel.value)
                }
            }
        }
    }
}
