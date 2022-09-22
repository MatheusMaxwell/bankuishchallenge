package com.matheusmaxwell.bankuishchallenge.utils.extensions

import android.util.Log
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericErrorResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> Exception.toGenericErrorResult(): GenericResult<T> {
    Log.e("EXCEPTION", this.toString()+this.message)
    return when(this) {
        is HttpException -> {
            GenericResult.Error(GenericErrorResult.BadRequest(this))
        }
        else -> {
            GenericResult.Error(GenericErrorResult.Unknown(this))
        }
    }

}