package com.matheusmaxwell.bankuishchallenge.presentation.repositories

import com.google.common.truth.Truth
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.domain.useCases.FetchRepositoriesUseCase
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericErrorResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericSuccessResult
import com.matheusmaxwell.bankuishchallenge.utils.liveData.getOrAwaitValue
import com.matheusmaxwell.bankuishchallenge.utils.viewState.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class RepositoriesViewModelTest {

    private val fetchRepositoriesUseCase = mockk<FetchRepositoriesUseCase>()
    private val viewModel = RepositoriesViewModel(fetchRepositoriesUseCase = fetchRepositoriesUseCase)

    @Test
    fun `when fetchRepositories return ViewState_Success`() = runBlocking {
        val repositoryDomain = mockk<RepositoryDomain>(relaxed = true)
        val repositories = listOf(repositoryDomain)
        coEvery {
            fetchRepositoriesUseCase.execute("language:kotlin", 1)
        } returns GenericResult.Success(GenericSuccessResult.Populated(repositories))

        viewModel.fetchRepositories(1)

        Truth
            .assertThat(viewModel.viewStateRepositoriesLiveData.getOrAwaitValue())
            .isInstanceOf(ViewState.Success::class.java)
    }

    @Test
    fun `when fetchRepositories return ViewState_Empty`() = runBlocking {
        coEvery {
            fetchRepositoriesUseCase.execute("language:kotlin", 1)
        } returns GenericResult.Success(GenericSuccessResult.Empty())

        viewModel.fetchRepositories(1)

        Truth
            .assertThat(viewModel.viewStateRepositoriesLiveData.getOrAwaitValue())
            .isInstanceOf(ViewState.Empty::class.java)
    }

    @Test
    fun `when fetchRepositories return ViewState_Error`() = runBlocking {

        coEvery {
            fetchRepositoriesUseCase.execute("language:kotlin", 1)
        } returns GenericResult.Error(GenericErrorResult.Unknown(Throwable("")))

        viewModel.fetchRepositories(1)

        Truth
            .assertThat(viewModel.viewStateRepositoriesLiveData.getOrAwaitValue())
            .isInstanceOf(ViewState.Error::class.java)

    }

}