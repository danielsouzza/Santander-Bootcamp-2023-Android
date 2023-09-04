package com.example.devapp.views.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.devapp.data.model.Repository
import com.example.devapp.data.model.User
import com.example.devapp.ui.theme.Background_1
import com.example.devapp.ui.theme.Background_2
import com.example.devapp.ui.theme.TextColor
import com.example.devapp.views.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: MainViewModel
){

    val user by viewModel.userSate.collectAsState()
    val repositories by viewModel.repositories.collectAsState()

    Scaffold(
        containerColor = Background_1,
        topBar = { TopBar(user) }
    ) {
        Surface(
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
            color = Background_2,
            modifier= Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp)
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Projetos",
                    fontSize= 20.sp,
                    color= TextColor,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(repositories){repository->
                        RepositoryItem(repository = repository){
                            viewModel.shareRepository(repository.htmlUrl)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    shareUrl: ()-> Unit
){
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = repository.name
            )
            Icon(
                imageVector = Icons.Default.Share,
                tint = TextColor,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .clickable { shareUrl() }
            )
        }
    }
}

@Composable
fun TopBar(user: User){
    Surface (
        modifier=Modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Background_2
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = user.name,
                fontSize= 20.sp,
                color= TextColor,
                fontWeight = FontWeight(700)
            )
            AsyncImage(
                modifier= Modifier
                    .clip(CircleShape)
                    .size(60.dp),
                model = user.avatarUrl,
                contentDescription = null
            )

        }
    }
}