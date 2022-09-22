package com.matheusmaxwell.bankuishchallenge.domain.useCases

import com.matheusmaxwell.bankuishchallenge.data.repository.RepositoryRepo
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult

class FetchRepositoriesUseCase(private val repository: RepositoryRepo) {

    suspend fun execute(searchText: String, page: Int): GenericResult<List<RepositoryDomain>> = repository.fetchRepositories(searchText, page)

}