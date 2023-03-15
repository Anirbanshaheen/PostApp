package com.example.postapp.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.models.PostModel
import com.example.postapp.repository.PostRepository
import com.example.postapp.utils.ViewState
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val viewState = MutableLiveData<ViewState>(ViewState.NONE)

    // dataList for observe specific data
    private val _dataList = MutableLiveData<PostModel>()
    val dataList: LiveData<PostModel> = _dataList

    // All API needed form Repository
    fun getPost(): LiveData<PostModel> {

        viewState.value = ViewState.ProgressState(true)
        val responseBody = MutableLiveData<PostModel>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = postRepository.getPost()
            withContext(Dispatchers.Main) {
                viewState.value = ViewState.ProgressState(false)
                when (response) {
                    is NetworkResponse.Success -> {
                        responseBody.value = response.body
                    }
                    is NetworkResponse.ServerError -> {
                        val message = "দুঃখিত, এই মুহূর্তে আমাদের সার্ভার কানেকশনে সমস্যা হচ্ছে, কিছুক্ষণ পর আবার চেষ্টা করুন"
                        viewState.value = ViewState.ShowMessage(message)
                    }
                    is NetworkResponse.NetworkError -> {
                        val message = "দুঃখিত, এই মুহূর্তে আপনার ইন্টারনেট কানেকশনে সমস্যা হচ্ছে"
                        viewState.value = ViewState.ShowMessage(message)
                    }
                    is NetworkResponse.UnknownError -> {
                        val message = "কোথাও কোনো সমস্যা হচ্ছে, আবার চেষ্টা করুন"
                        viewState.value = ViewState.ShowMessage(message)
                    }
                }
            }
        }
        return responseBody
    }
}