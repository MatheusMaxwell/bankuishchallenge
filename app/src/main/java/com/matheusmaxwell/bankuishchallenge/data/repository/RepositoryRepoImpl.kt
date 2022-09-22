package com.matheusmaxwell.bankuishchallenge.data.repository

import com.matheusmaxwell.bankuishchallenge.data.dataSource.RepositoryRemoteDataSource
import com.matheusmaxwell.bankuishchallenge.data.mappers.toDomain
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.utils.extensions.toGenericErrorResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericResult
import com.matheusmaxwell.bankuishchallenge.utils.genericResult.GenericSuccessResult
import retrofit2.HttpException
import java.lang.Exception

class RepositoryRepoImpl(
    private val dataSourceRemote: RepositoryRemoteDataSource
) : RepositoryRepo {
    override suspend fun fetchRepositories(searchText: String, page: Int): GenericResult<List<RepositoryDomain>> {
        return try {
            dataSourceRemote.fetchRepositories(searchText, page).let { response ->
                if (response.code() == 200) {
                    val repositories = response.body()?.items
                    if (repositories.isNullOrEmpty()) {
                        GenericResult.Success(GenericSuccessResult.Empty())
                    } else {
                        GenericResult.Success(GenericSuccessResult.Populated(repositories.toDomain()))
                    }
                } else {
                    throw HttpException(response)
                }
            }
        } catch (exception: Exception) {
            exception.toGenericErrorResult()
        }
    }
}