package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.DeleteTaskRequest
import com.example.onemoretick.usecase.DeleteTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteTaskViewModel : BaseViewModel() {
    private val deleteTaskUseCase = DeleteTaskUseCase()
    val deleteTaskSuccess = MutableLiveData<Boolean>()

    fun deleteTask(deleteTaskRequest: DeleteTaskRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = deleteTaskUseCase(deleteTaskRequest)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> deleteTaskSuccess.postValue(true)
            }
        }
    }
}
