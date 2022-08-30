package com.example.imageloader

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchImagesUseCase: FetchImagesUseCase,
    private val insertImagesUseCase: InsertImagesUseCase,
    private val readImagesUseCase: ReadImagesUseCase
) :
    ViewModel() {
    private var _myList = MutableLiveData<List<ImageData>>()
    val myList: LiveData<List<ImageData>> get() = _myList

    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val serverResponse = fetchImagesUseCase.invoke().value
            val sss= serverResponse?.files?.map { it.isNewItem = true }
            val value = insertImagesUseCase.invoke(serverResponse?.files ?: emptyList())
            _myList.postValue(readImagesUseCase.invoke())
            Log.e("Mohammad", value.joinToString { it.toString() })
        }
    }
}