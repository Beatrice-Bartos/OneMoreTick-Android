package com.example.onemoretick.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onemoretick.base.BaseViewModel
import com.example.onemoretick.models.base.Result
import com.example.onemoretick.models.result.CategoryResponse
import com.example.onemoretick.usecase.GetCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCategoriesViewModel : BaseViewModel() {
    private val getCategoriesUseCase = GetCategoriesUseCase()
    val getCategoriesSuccess = MutableLiveData<List<CategoryResponse>>()

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCategoriesUseCase(null)
            when (result) {
                is Result.Error -> setError(result.value)
                is Result.Success -> getCategoriesSuccess.postValue(result.value!!)
            }
        }
    }
}
