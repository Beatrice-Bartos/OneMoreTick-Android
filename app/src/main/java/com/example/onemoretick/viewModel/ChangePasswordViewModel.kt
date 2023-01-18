package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.usecase.ChangePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePasswordViewModel : BaseViewModel() {
    private val changePasswordUseCase = ChangePasswordUseCase()
    private val changePasswordSuccess = MutableLiveData<Boolean>()

    fun changePassword(user: CreateNewPasswordRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = changePasswordUseCase(user)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> changePasswordSuccess.postValue(true)
            }
        }
    }
}
