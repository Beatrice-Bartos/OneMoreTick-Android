package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.models.result.RegisterUserResponse
import com.example.onemoretick.usecase.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    private val registerUserUseCase = RegisterUserUseCase()
    val registerSuccess = MutableLiveData<LoginUserResponse>()

    fun registerUser(user: RegisterUserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = registerUserUseCase(user)) {
                is Result.Error -> setError(result.value)
                is Result.Success -> registerSuccess.postValue(result.value!!)
            }
        }
    }
}

