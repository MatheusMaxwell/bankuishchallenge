package com.matheusmaxwell.bankuishchallenge.data.dataSource

import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import retrofit2.Response

class RepositoryRemoteDataSourceImpl(
    private var api: ApiService
): RepositoryRemoteDataSource {
    override suspend fun fetchRepositories(searchText: String, page: Int): Response<RepositoryResponse> = api.fetchRepositories(searchText, 20, page)
}