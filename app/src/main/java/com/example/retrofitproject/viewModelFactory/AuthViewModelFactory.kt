package com.example.retrofitproject.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.viewModel.LoginViewModel


class AuthViewModelFactory(val app: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(app,repository) as T
    }
}