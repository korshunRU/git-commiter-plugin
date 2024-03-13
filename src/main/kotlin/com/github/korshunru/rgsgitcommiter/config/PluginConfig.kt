package com.github.korshunru.rgsgitcommiter.config

import com.github.korshunru.rgsgitcommiter.RgsBundle
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class PluginConfig: Configurable {

    private var component: PluginSettingsComponent?
    private var settings: PluginSettingsState = PluginSettingsState.instance

    init {
        component = PluginSettingsComponent().also {
            settings.jiraUrl
                .takeIf { it.isEmpty() }
                ?.let { settings.jiraUrl = RgsBundle.message("jiraBaseUrl") }
            settings.branchNamePattern
                .takeIf { it.isEmpty() }
                ?.let { settings.branchNamePattern = RgsBundle.message("branchNamePattern") }
        }
    }

    override fun getDisplayName(): String = RgsBundle.message("settingsTitle")

    override fun createComponent(): JComponent? = component?.create()

    override fun apply() {
        settings.jiraUrl = component?.jiraUrlTextField?.text
            ?: RgsBundle.message("jiraBaseUrl")
        settings.jiraToken = component?.jiraTokenTextField?.text ?: ""
        settings.branchNamePattern = component?.branchNamePatternTextField?.text
            ?: RgsBundle.message("branchNamePattern")
        println("KotlinDSLUISampleConfigurable apply() called")
    }

    override fun isModified(): Boolean {
        val isModified = settings.jiraUrl != component?.jiraUrlTextField?.text ||
                settings.jiraToken != component?.jiraTokenTextField?.text ||
                settings.branchNamePattern != component?.branchNamePatternTextField?.text
        println("KotlinDSLUISampleConfigurable isModified() called, return $isModified")
        return isModified
    }

    override fun reset() {
        component?.jiraUrlTextField?.text = settings.jiraUrl
        component?.jiraTokenTextField?.text = settings.jiraToken
        component?.branchNamePatternTextField?.text = settings.branchNamePattern
        println("KotlinDSLUISampleConfigurable reset() called")
    }

    override fun disposeUIResources() {
        component = null
    }

}