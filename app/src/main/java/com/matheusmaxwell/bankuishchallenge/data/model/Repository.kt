package com.matheusmaxwell.bankuishchallenge.data.model

import com.google.gson.annotations.SerializedName

data class Repository (
    var id: Long,
    var name: String?,
    @SerializedName("full_name")
    var fullName: String?,
    var private: Boolean?,
    var description: String?,
    @SerializedName("html_url")
    var url: String?,
    var language: String?,
    var forks: Int?,
    @SerializedName("open_issues")
    var openIssues: Int?,
    var watchers: Int?,
    var topics: List<String>?,
    var owner: Owner?
)