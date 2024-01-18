package com.github.korshunru.rgsgitcommiter.providers

import com.github.korshunru.rgsgitcommiter.services.RepositoryService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.LocalChangeList
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider

class GitMessageTagCheckerProvider : CommitMessageProvider {
    override fun getCommitMessage(localChangeList: LocalChangeList, project: Project): String =
        project.service<RepositoryService>().getBranchName()

}
