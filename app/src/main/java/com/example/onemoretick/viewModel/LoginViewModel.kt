package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.usecase.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val loginUserUseCase = LoginUserUseCase()
    val loginSuccess = MutableLiveData<Boolean>()

    fun loginUser(user: LoginUserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUserUseCase(user)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> loginSuccess.postValue(true)
            }
        }
    }
}
