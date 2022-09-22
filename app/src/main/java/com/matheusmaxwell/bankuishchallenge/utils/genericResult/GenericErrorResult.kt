package com.matheusmaxwell.bankuishchallenge.utils.genericResult

sealed class GenericErrorResult<T> {
    class BadRequest<T>(val throwable: Throwable) : GenericErrorResult<T>()
    class Unknown<T>(val throwable: Throwable) : GenericErrorResult<T>()
}
