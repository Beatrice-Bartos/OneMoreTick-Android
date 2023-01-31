package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.usecase.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val loginUserUseCase = LoginUserUseCase()
    val loginSuccess = MutableLiveData<LoginUserResponse>()

    fun loginUser(user: LoginUserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = loginUserUseCase(user)) {
                is Result.Error -> setError(result.value)
                is Result.Success -> {
                    loginSuccess.postValue(result.value!!)
                }
            }
        }
    }
}
