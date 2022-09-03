package com.example.imageloader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FetchImagesUseCase
import com.example.domain.usecase.InsertImagesUseCase
import com.example.domain.usecase.ReadImagesUseCase
import com.example.domain.usecase.SetAllDataIsLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchImagesUseCase: FetchImagesUseCase,
    private val insertImagesUseCase: InsertImagesUseCase,
    private val readImagesUseCase: ReadImagesUseCase,
    private val setAllDataIsLocalUseCase: SetAllDataIsLocalUseCase,
) :
    ViewModel() {
    private var _myList = MutableLiveData<List<ImageDataView>>()
    val myList: LiveData<List<ImageDataView>> get() = _myList

    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchImagesUseCase.invoke().collect { myResponseDomainModel ->
                    myResponseDomainModel.files.map { it.isNewItem = true }
                    insertImagesUseCase.invoke(myResponseDomainModel.files)
                    _myList.postValue(readImagesUseCase.invoke().map { it.toImageDataView() })
                }
            } catch (e: Exception) {
                setAllDataIsLocalUseCase.invoke()
                _myList.postValue(readImagesUseCase.invoke().map { it.toImageDataView() })
            }
        }
    }
}