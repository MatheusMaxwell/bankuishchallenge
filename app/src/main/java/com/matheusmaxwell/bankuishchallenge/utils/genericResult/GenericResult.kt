package com.matheusmaxwell.bankuishchallenge.utils.genericResult

sealed class GenericResult<T> {
    class Success<T>(
        val success: GenericSuccessResult<T>
    ) : GenericResult<T>()

    class Error<T>(
        val error: GenericErrorResult<T>
    ) : GenericResult<T>()
}