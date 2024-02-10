package com.github.korshunru.rgsgitcommiter.services

import com.github.korshunru.rgsgitcommiter.RgsBundle
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import git4idea.repo.GitRepositoryManager

@Service(Service.Level.PROJECT)
class RepositoryService(private val project: Project) {

    fun getBranchName(): String =
        GitRepositoryManager
            .getInstance(project)
            .repositories.firstOrNull()
            ?.currentBranch
            ?.name
            ?.let { String.format(RgsBundle.message("labelTemplate"),
                it.substring(it.indexOf("/") + 1, it.length)) }
            ?: RgsBundle.message("labelDefaultTemplate")
}
