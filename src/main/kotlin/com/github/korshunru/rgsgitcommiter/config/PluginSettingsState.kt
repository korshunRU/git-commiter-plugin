package com.github.korshunru.rgsgitcommiter.config

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.github.korshunru.rgsgitcommiter.config.PluginSettingsState",
    storages = [Storage("RgsCommiterSettings.xml")]
)
class PluginSettingsState: PersistentStateComponent<PluginSettingsState> {

    var jiraUrl: String = ""
    var jiraToken: String = ""
    var branchNamePattern: String = ""

    companion object {
        val instance: PluginSettingsState =
            ApplicationManager.getApplication().getService(PluginSettingsState::class.java)
    }

    override fun getState(): PluginSettingsState = this

    override fun loadState(state: PluginSettingsState) =
        XmlSerializerUtil.copyBean(state, this)
}