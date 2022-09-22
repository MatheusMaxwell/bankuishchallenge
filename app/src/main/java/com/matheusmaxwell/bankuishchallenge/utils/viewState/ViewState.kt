package com.matheusmaxwell.bankuishchallenge.utils.viewState

sealed class ViewState<T> {
    class Success<T>(val data: T) : ViewState<T>()
    class Loading<T> : ViewState<T>()
    class Empty<T> : ViewState<T>()
    class Error<T> : ViewState<T>()
}