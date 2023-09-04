package com.example.devapp.views.viewModel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.devapp.data.PreferenceDataStore
import com.example.devapp.data.model.Repository
import com.example.devapp.data.model.User
import com.example.devapp.data.network.AppServe
import com.example.devapp.views.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val navHostController: NavHostController,
    val dataStore: PreferenceDataStore,
    val startActivity: (Intent)->Unit
): ViewModel() {
    private val server = AppServe.providerRetrofit

    private val _userState = MutableStateFlow(User())
    val userSate:StateFlow<User> get() = _userState

    private val _repositories = MutableStateFlow(listOf<Repository>())
    val repositories: StateFlow<List<Repository>> get() = _repositories

    val progressBar = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            try {
                val username = dataStore.getUsername()
                if(username != null){
                    getUser(username)
                }else{
                    navHostController.popBackStack()
                    navHostController.navigate(Route.Login.route)
                }
            }catch (e:Throwable){
               println(e.message)
            }
        }
    }


    private fun getRepositories(){
        viewModelScope.launch {
            try {
                _repositories.value = server.getRepositories(_userState.value.login)
            }catch (e: Throwable){
                println(e.message)
            }
        }
    }

    fun shareRepository(uriRepository: String){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, uriRepository)
            type = "text/plain"
        }

        val sharedIntent = Intent.createChooser(sendIntent,null)
        startActivity(sharedIntent)
    }

    fun getUser(username:String){
        progressBar.value = true
        viewModelScope.launch {
            try {
                _userState.value = server.getUser(username)
                dataStore.saveUsername(_userState.value.login)
                getRepositories()
                progressBar.value = false
                navHostController.popBackStack()
                navHostController.navigate(route = Route.Home.route)
            }catch (e:Throwable){
                progressBar.value = false
            }
        }
    }
}