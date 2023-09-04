package com.example.devapp.views.screens

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.devapp.views.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel
){
    var usernameState by remember{ mutableStateOf("") }
    val progressBarState by viewModel.progressBar.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "GitHub",
            fontSize = 20.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = usernameState,
            onValueChange = { usernameState = it},
            label = {
                Text(text = "Username")
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                viewModel.getUser(usernameState)
                if(viewModel.userSate.value.name.isNotEmpty()){
                    navHostController.navigate("view_projects")
                }
            },
            modifier = Modifier.size(width = 200.dp, height = 50.dp)
        ) {
            if (progressBarState){
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(35.dp))
            }else{
                Text(
                    text = "CONFIRMAR",
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp

                )
            }
        }
    }
}
