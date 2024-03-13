package com.github.korshunru.rgsgitcommiter.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class TaskInfoHandler: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("========= TaskInfoHandler actionPerformed")
    }

    override fun update(e: AnActionEvent) {
        println("========= TaskInfoHandler update")
    }
}