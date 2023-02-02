package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.models.request.EditTaskRequest
import com.example.onemoretick.usecase.EditTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTaskViewModel : BaseViewModel() {
    private val editTaskUseCase = EditTaskUseCase()
    private val editTaskSuccess = MutableLiveData<Boolean>()

    fun editTask(editTaskRequest: EditTaskRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = editTaskUseCase(editTaskRequest)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> editTaskSuccess.postValue(true)
            }
        }
    }
}
