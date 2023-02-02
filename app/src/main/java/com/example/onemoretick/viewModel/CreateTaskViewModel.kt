package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.usecase.CreateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskViewModel : BaseViewModel() {
    private val createTaskUseCase = CreateTaskUseCase()
    val createTaskSuccess = MutableLiveData<TaskResponse>()

    fun createTask(createTaskRequest: CreateTaskRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = createTaskUseCase(createTaskRequest)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> createTaskSuccess.postValue(result.value!!)
            }
        }
    }
}
