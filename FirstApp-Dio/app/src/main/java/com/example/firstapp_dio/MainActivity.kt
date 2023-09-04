package com.example.firstapp_dio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstapp_dio.ui.theme.FirstAppDioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppDioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var idTitleHome by remember {
        mutableStateOf(R.string.title_home_pt)
    }
    var idBtnText by remember{
        mutableStateOf(R.string.title_btn_pt)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = idTitleHome),
            modifier = modifier
        )
        Button(
            onClick = {
                if(idTitleHome == R.string.title_home_pt){
                    idTitleHome = R.string.title_home_en
                    idBtnText = R.string.title_btn_en
                }else{
                    idTitleHome = R.string.title_home_pt
                    idBtnText = R.string.title_btn_pt
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_translate),
                contentDescription = "",
                modifier= Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = stringResource(id = idBtnText))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAppDioTheme {
        Greeting()
    }
}