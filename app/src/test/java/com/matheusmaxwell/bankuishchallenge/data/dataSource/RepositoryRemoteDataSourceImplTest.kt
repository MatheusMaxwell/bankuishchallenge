package com.matheusmaxwell.bankuishchallenge.data.dataSource

import com.google.common.truth.Truth
import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Response


@RunWith(RobolectricTestRunner::class)
class RepositoryRemoteDataSourceImplTest{
    private val api = mockk<ApiService>()
    private val dataSource = RepositoryRemoteDataSourceImpl(api = api)

    @Test
    fun `when fetchRepositories return RepositoryResponse with success`() = runBlocking{
        val response = mockk<RepositoryResponse>()
        coEvery {
            api.fetchRepositories("", 20, 1)
        } returns Response.success(200, response)

        val result = dataSource.fetchRepositories("", 1)

        Truth
            .assertThat(result.body())
            .isEqualTo(response)
    }

}