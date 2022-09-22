package com.matheusmaxwell.bankuishchallenge.data.repository

import com.matheusmaxwell.bankuishchallenge.data.dataSource.RepositoryRemoteDataSource
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult


interface RepositoryRepo {

    suspend fun fetchRepositories(searchText: String, page: Int): GenericResult<List<RepositoryDomain>>

}