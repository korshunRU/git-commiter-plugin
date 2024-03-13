package com.github.korshunru.rgsgitcommiter.providers

import com.github.korshunru.rgsgitcommiter.RgsBundle
import com.github.korshunru.rgsgitcommiter.config.PluginSettingsState
import com.github.korshunru.rgsgitcommiter.services.RepositoryService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.LocalChangeList
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider

class GitMessageTagCheckerProvider : CommitMessageProvider {

    private val settings: PluginSettingsState = PluginSettingsState.instance

    override fun getCommitMessage(localChangeList: LocalChangeList, project: Project): String {
        val formattedBranchName = project
            .service<RepositoryService>()
            .getBranchName()
            ?.let {
                String.format(
                    settings.branchNamePattern,
                    it.substring(it.indexOf("/") + 1, it.length)) }
        return formattedBranchName ?: RgsBundle.message("branchNamePatternDefault")
    }

}
