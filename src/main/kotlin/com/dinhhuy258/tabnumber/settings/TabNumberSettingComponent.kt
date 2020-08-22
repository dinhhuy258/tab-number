package com.dinhhuy258.tabnumber.settings

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel
import org.jetbrains.annotations.NotNull

class TabNumberSettingComponent {
    private val tabNumberSettingMainPanel: JPanel

    private val tabNumberSeparatorSettingText = JBTextField(5)

    init {
        tabNumberSettingMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(
                        JBLabel("Enter tab number separator: "),
                        tabNumberSeparatorSettingText,
                        1,
                        false)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }

    fun getPanel() = tabNumberSettingMainPanel

    fun getPreferredFocusedComponent() = tabNumberSeparatorSettingText

    fun getTabNumberSeparator() = tabNumberSeparatorSettingText.text ?: ""

    fun setTabNumberSeparator(@NotNull tabNumberSeparator: String) {
        tabNumberSeparatorSettingText.text = tabNumberSeparator
    }
}
