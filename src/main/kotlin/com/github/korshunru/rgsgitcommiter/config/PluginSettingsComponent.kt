package com.github.korshunru.rgsgitcommiter.config

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.JBColor
import com.intellij.ui.dsl.builder.panel
import javax.swing.JTextField

class PluginSettingsComponent {
    lateinit var jiraUrlTextField: JTextField
    lateinit var jiraTokenTextField: JTextField
    lateinit var branchNamePatternTextField: JTextField

    fun create(): DialogPanel =
        panel {
            row("Jira Base Url: ") {
                textField().also {
                    jiraUrlTextField = it.component
//                    println("==== ${it.component.size}")
//                    it.component.size = Dimension(550, 30)
//                    println("==== ${it.component.size}")
//                    it.component .background = Color.RED
                }
            }
            row("Jira Token: ") {
                textField().also { jiraTokenTextField = it.component }
            }
            separator()
            row("Branch Name Pattern: ") {
                textField().also { branchNamePatternTextField = it.component }
            }
        }.also {
            it.background = JBColor.BLUE
        }
}