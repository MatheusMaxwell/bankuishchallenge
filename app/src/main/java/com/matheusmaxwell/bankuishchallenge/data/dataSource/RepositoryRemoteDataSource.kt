package com.matheusmaxwell.bankuishchallenge.data.dataSource

import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import retrofit2.Response

interface RepositoryRemoteDataSource {
    suspend fun fetchRepositories(searchText: String, page: Int): Response<RepositoryResponse>
}