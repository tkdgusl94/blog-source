package com.leveloper.data.service

import com.leveloper.data.model.GithubRepoRes
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{owner}/repos")
    fun getRepos(
        @Path("owner") owner: String
    ) : List<GithubRepoRes>
}