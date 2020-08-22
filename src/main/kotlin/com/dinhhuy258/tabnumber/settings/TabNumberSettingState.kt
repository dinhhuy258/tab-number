package com.dinhhuy258.tabnumber.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
        name = "com.dinhhuy258.tabnumber.settings.TabNumberSettingState",
        storages = [Storage("TabNumberPlugin.xml")]
)
class TabNumberSettingState : PersistentStateComponent<TabNumberSettingState> {
    companion object {
        const val DEFAULT_TAB_NUMBER_SEPARATOR = ". "

        fun getInstance(): TabNumberSettingState {
            return ServiceManager.getService(TabNumberSettingState::class.java)
        }
    }

    var tabNumberSeparator = DEFAULT_TAB_NUMBER_SEPARATOR

    override fun getState(): TabNumberSettingState? {
        return this
    }

    override fun loadState(state: TabNumberSettingState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}
