package com.matheusmaxwell.bankuishchallenge.data.mappers

import com.matheusmaxwell.bankuishchallenge.data.model.Owner
import com.matheusmaxwell.bankuishchallenge.data.model.Repository
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain

fun Repository.toDomain(): RepositoryDomain {
    return RepositoryDomain(
        id = id,
        name = name ?: "",
        fullName = fullName ?: "",
        private = private ?: false,
        description = description ?: "",
        url = url ?: "",
        language = language ?: "",
        forks = forks ?: -1,
        openIssues = openIssues ?: -1,
        watchers = watchers ?: -1,
        topics = topics ?: emptyList(),
        owner = owner
    )
}

fun List<Repository>.toDomain(): List<RepositoryDomain> {
    val list = mutableListOf<RepositoryDomain>()
    repeat(count()) { index ->
        this[index].apply {
            list.add(
                this.toDomain()
            )
        }
    }
    return list
}