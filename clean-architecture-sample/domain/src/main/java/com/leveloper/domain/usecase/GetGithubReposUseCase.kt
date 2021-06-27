package com.leveloper.domain.usecase

import com.leveloper.domain.model.GithubRepo
import com.leveloper.domain.repository.GithubRepository
import kotlinx.coroutines.*

class GetGithubReposUseCase(private val githubRepository: GithubRepository) {

    operator fun invoke(
        owner: String,
        scope: CoroutineScope,
        onResult: (List<GithubRepo>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                githubRepository.getRepos(owner)
            }
            onResult(deferred.await())
        }
    }
}