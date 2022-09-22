package com.matheusmaxwell.bankuishchallenge.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("total_count")
    var totalCount: Long,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    var items: List<Repository>
)