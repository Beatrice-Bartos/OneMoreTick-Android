package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.usecase.GetTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetTasksViewModel : BaseViewModel() {
    private val getTasksUseCase = GetTasksUseCase()
    val getTasksSuccess = MutableLiveData<List<TaskResponse>>()

    fun getTasks(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getTasksUseCase(userId)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> getTasksSuccess.postValue(result.value!!)
            }
        }
    }
}
