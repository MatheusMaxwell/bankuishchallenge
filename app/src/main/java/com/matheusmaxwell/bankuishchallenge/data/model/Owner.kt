package com.matheusmaxwell.bankuishchallenge.data.model

import com.google.gson.annotations.SerializedName

data class Owner (
    var id: Long,
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
)