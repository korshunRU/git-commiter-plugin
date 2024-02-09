package com.github.korshunru.rgsgitcommiter.actions

import com.github.korshunru.rgsgitcommiter.services.RepositoryService
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.vcs.VcsDataKeys
import com.intellij.openapi.vcs.ui.CommitMessage
import com.intellij.openapi.vcs.ui.Refreshable

class HandlerButton: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val data =
            when {
                Refreshable.PANEL_KEY.getData(e.dataContext) is CommitMessage ->
                    Refreshable.PANEL_KEY.getData(e.dataContext) as CommitMessage
                VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.dataContext) != null ->
                    VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.dataContext)
                else -> null
            }
        data?.setCommitMessage(e.project?.service<RepositoryService>()?.getBranchName())
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.icon = AllIcons.Actions.Commit
    }
}