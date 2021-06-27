package com.leveloper.domain.repository

import com.leveloper.domain.model.GithubRepo

interface GithubRepository {
    suspend fun getRepos(owner: String): List<GithubRepo>
}