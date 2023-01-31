package com.example.onemoretick.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()

    protected fun setError(exception: Exception) {
        error.postValue(exception.message ?: "Unknown error")
    }
}
