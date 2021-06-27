package com.leveloper.data

import com.leveloper.data.source.GithubRemoteSource
import com.leveloper.domain.model.GithubRepo
import com.leveloper.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteSource: GithubRemoteSource
) : GithubRepository {

    override suspend fun getRepos(owner: String): List<GithubRepo> {
        return githubRemoteSource.getRepos(owner)
    }
}