package com.matheusmaxwell.bankuishchallenge.data.dataSource

import com.matheusmaxwell.bankuishchallenge.data.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/repositories")
    suspend fun fetchRepositories(@Query("q") searchText: String, @Query("per_page") perPage: Int, @Query("page") page: Int) : Response<RepositoryResponse>

}