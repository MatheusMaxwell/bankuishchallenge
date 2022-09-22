package com.matheusmaxwell.bankuishchallenge.utils.extensions

import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericErrorResult
import com.matheusmaxwell.bankuishchallenge.utils.viewState.ViewState
import retrofit2.HttpException

fun <E, V> GenericErrorResult<E>.toViewStateError(): ViewState.Error<V> {
    return when (this) {
        is GenericErrorResult.Unknown -> {
            ViewState.Error()
        }
        is GenericErrorResult.BadRequest -> {
            ViewState.Error()
        }
    }
}