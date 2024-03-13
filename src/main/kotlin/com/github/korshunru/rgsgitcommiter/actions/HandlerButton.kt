package com.github.korshunru.rgsgitcommiter.actions

import com.github.korshunru.rgsgitcommiter.config.PluginSettingsState
import com.github.korshunru.rgsgitcommiter.services.RepositoryService
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.vcs.VcsDataKeys
import com.intellij.openapi.vcs.ui.CommitMessage
import com.intellij.openapi.vcs.ui.Refreshable

class HandlerButton: AnAction() {

    private val settings: PluginSettingsState = PluginSettingsState.instance

    override fun actionPerformed(e: AnActionEvent) {
        val data =
            when {
                Refreshable.PANEL_KEY.getData(e.dataContext) is CommitMessage ->
                    Refreshable.PANEL_KEY.getData(e.dataContext) as CommitMessage
                VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.dataContext) != null ->
                    VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.dataContext)
                else -> null
            }
        val formattedBranchName = e.project
            ?.service<RepositoryService>()
            ?.getBranchName()
            ?.let {
                String.format(
                    settings.branchNamePattern,
                    it.substring(it.indexOf("/") + 1, it.length)) }
        data?.setCommitMessage(formattedBranchName)
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.icon = AllIcons.Actions.Commit
    }
}