package com.matheusmaxwell.bankuishchallenge.presentation.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.domain.useCases.FetchRepositoriesUseCase
import com.matheusmaxwell.bankuishchallenge.utils.extensions.toViewStateError
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericSuccessResult
import com.matheusmaxwell.bankuishchallenge.utils.viewState.ViewState
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val fetchRepositoriesUseCase: FetchRepositoriesUseCase
): ViewModel() {

    private val _viewStateRepositoriesLiveData =
        MutableLiveData<ViewState<List<RepositoryDomain>>>()
    val viewStateRepositoriesLiveData: LiveData<ViewState<List<RepositoryDomain>>> =
        _viewStateRepositoriesLiveData


    fun fetchRepositories(searchText: String, page: Int){
        _viewStateRepositoriesLiveData.value = ViewState.Loading()
        viewModelScope.launch {
            when(val result = fetchRepositoriesUseCase.execute(searchText, page)){
                is GenericResult.Success -> {
                    when (result.success) {
                        is GenericSuccessResult.Populated -> {
                            _viewStateRepositoriesLiveData.value =
                                ViewState.Success((result.success).data)
                        }
                        is GenericSuccessResult.Empty -> {
                            _viewStateRepositoriesLiveData.value = ViewState.Empty()
                        }
                    }
                }
                is GenericResult.Error -> {
                    _viewStateRepositoriesLiveData.value = result.error.toViewStateError()
                }
            }
        }
    }

}