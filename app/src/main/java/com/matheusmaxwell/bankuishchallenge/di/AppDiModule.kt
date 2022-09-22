package com.matheusmaxwell.bankuishchallenge.di

import com.matheusmaxwell.bankuishchallenge.data.dataSource.ApiService
import com.matheusmaxwell.bankuishchallenge.data.dataSource.RepositoryRemoteDataSource
import com.matheusmaxwell.bankuishchallenge.data.dataSource.RepositoryRemoteDataSourceImpl
import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import com.matheusmaxwell.bankuishchallenge.data.network.Network.provideOkHttpClient
import com.matheusmaxwell.bankuishchallenge.data.network.Network.provideRetrofit
import com.matheusmaxwell.bankuishchallenge.data.repository.RepositoryRepo
import com.matheusmaxwell.bankuishchallenge.data.repository.RepositoryRepoImpl
import com.matheusmaxwell.bankuishchallenge.domain.useCases.FetchRepositoriesUseCase
import com.matheusmaxwell.bankuishchallenge.presentation.repositories.RepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object AppDiModule {

    private const val RETROFIT_SCOPE_NAME = "retrofit_scope"

    val modules = module {
        single {
            provideOkHttpClient()
        }
        single (named(RETROFIT_SCOPE_NAME)) {
            provideRetrofit(okHttpClient = get())
        }

        single<ApiService> {
            get<Retrofit>(named(RETROFIT_SCOPE_NAME)).create(ApiService::class.java)
        }

        single<RepositoryRemoteDataSource>{
            RepositoryRemoteDataSourceImpl(api = get())
        }

        single<RepositoryRepo>{
            RepositoryRepoImpl(dataSourceRemote = get())
        }

        single {
            FetchRepositoriesUseCase(repository = get())
        }

        viewModel {
            RepositoriesViewModel(fetchRepositoriesUseCase = get())
        }

    }
}