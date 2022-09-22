package com.matheusmaxwell.bankuishchallenge.data.repository

import com.google.common.truth.Truth
import com.matheusmaxwell.bankuishchallenge.data.dataSource.RepositoryRemoteDataSource
import com.matheusmaxwell.bankuishchallenge.data.model.Repository
import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericErrorResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericSuccessResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
class RepositoryRepoImplTest {

    private val dataSourceRemote = mockk<RepositoryRemoteDataSource>(relaxed = true)
    private val repository = RepositoryRepoImpl(dataSourceRemote)

    private val responseBodyError = "".toResponseBody("application/json".toMediaTypeOrNull())

    @Test
    fun `when repository fetchRepositories return GenericSuccessResult_Populated`() = runBlocking {
        val repositoryModel = mockk<Repository>(relaxed = true)
        val response = RepositoryResponse(totalCount = 1, incompleteResults = false, items = listOf(repositoryModel))
        coEvery {
            dataSourceRemote.fetchRepositories("", 1)
        } returns Response.success(200, response)

        val result = repository.fetchRepositories("", 1) as GenericResult.Success

        Truth
            .assertThat(result.success)
            .isInstanceOf(GenericSuccessResult.Populated::class.java)
    }

    @Test
    fun `when repository fetchRepositories return GenericSuccessResult_Empty`() = runBlocking {

        val response = RepositoryResponse(totalCount = 1, incompleteResults = false, items = emptyList())
        coEvery {
            dataSourceRemote.fetchRepositories("", 1)
        } returns Response.success(200, response)

        val result = repository.fetchRepositories("", 1) as GenericResult.Success

        Truth
            .assertThat(result.success)
            .isInstanceOf(GenericSuccessResult.Empty::class.java)
    }

    @Test
    fun `when repository fetchRepositories return GenericErrorResult_BadRequest`() = runBlocking {

        coEvery {
            dataSourceRemote.fetchRepositories("", 1)
        } returns Response.error(500, responseBodyError)

        val result = repository.fetchRepositories("", 1) as GenericResult.Error

        Truth
            .assertThat(result.error)
            .isInstanceOf(GenericErrorResult.BadRequest::class.java)

    }

    @Test
    fun `when repository fetchRepositories return GenericErrorResult_Unknown`() = runBlocking {

        coEvery {
            dataSourceRemote.fetchRepositories("", 1)
        } throws  Exception("")

        val result = repository.fetchRepositories("", 1) as GenericResult.Error

        Truth
            .assertThat(result.error)
            .isInstanceOf(GenericErrorResult.Unknown::class.java)

    }

}