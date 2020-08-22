package com.dinhhuy258.tabnumber.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class TabNumberSettingConfigurable : Configurable {
    private lateinit var tabNumberSettingComponent: TabNumberSettingComponent

    override fun getDisplayName(): String {
        return "Tab Number"
    }

    override fun createComponent(): JComponent? {
        tabNumberSettingComponent = TabNumberSettingComponent()
        return tabNumberSettingComponent.getPanel()
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return tabNumberSettingComponent.getPreferredFocusedComponent()
    }

    override fun isModified(): Boolean {
        val settings = TabNumberSettingState.getInstance()
        return tabNumberSettingComponent.getTabNumberSeparator() != settings.tabNumberSeparator
    }

    override fun apply() {
        val settings = TabNumberSettingState.getInstance()
        settings.tabNumberSeparator = tabNumberSettingComponent.getTabNumberSeparator()
    }

    override fun reset() {
        val settings = TabNumberSettingState.getInstance()
        tabNumberSettingComponent.setTabNumberSeparator(settings.tabNumberSeparator)
    }
}
