package com.matheusmaxwell.bankuishchallenge.domain.model

import com.google.gson.annotations.SerializedName
import com.matheusmaxwell.bankuishchallenge.data.model.Owner

class RepositoryDomain(
    var id: Long,
    var name: String,
    var fullName: String,
    var private: Boolean,
    var description: String,
    var url: String,
    var language: String,
    var forks: Int,
    var openIssues: Int,
    var watchers: Int,
    var topics: List<String>,
    var owner: Owner?
)
