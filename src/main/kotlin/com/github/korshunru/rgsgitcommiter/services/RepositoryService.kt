package com.github.korshunru.rgsgitcommiter.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import git4idea.repo.GitRepositoryManager

@Service(Service.Level.PROJECT)
class RepositoryService(private val project: Project) {

    fun getBranchName(): String? =
        GitRepositoryManager
            .getInstance(project)
            .repositories.firstOrNull()
            ?.currentBranch
            ?.name
            ?.let { it.substring(it.indexOf("/") + 1, it.length) }
}
