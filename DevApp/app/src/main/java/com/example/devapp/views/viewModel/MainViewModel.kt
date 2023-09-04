package com.example.devapp.views.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.devapp.data.model.Repository
import com.example.devapp.data.model.User
import com.example.devapp.data.network.AppServe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val navHostController: NavHostController
): ViewModel() {
    private val server = AppServe.providerRetrofit

    private val _userState = MutableStateFlow(User())
    val userSate:StateFlow<User> get() = _userState

    private val _repositories = MutableStateFlow(listOf<Repository>())
    val repositories: StateFlow<List<Repository>> get() = _repositories

    val progressBar = MutableStateFlow(false)


    private fun getRepositories(){
        viewModelScope.launch {
            try {
                _repositories.value = server.getRepositories(_userState.value.login)
            }catch (e: Throwable){
                println(e.message)
            }
        }
    }

    fun getUser(username:String){
        progressBar.value = true
        viewModelScope.launch {
            try {
                _userState.value = server.getUser(username)
                getRepositories()
                progressBar.value = false
                navHostController.navigate("view_projects")
            }catch (e:Throwable){
                progressBar.value = false
            }
        }
    }
}