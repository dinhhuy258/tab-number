package com.dinhhuy258.tabnumber.listeners

import com.dinhhuy258.tabnumber.settings.TabNumberSettingState
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.tabs.JBTabs
import com.intellij.ui.tabs.TabsListener

class TabNumberFileEditorManagerListener(project: Project) : FileEditorManagerListener {
    private val tabNumberSettingState: TabNumberSettingState = TabNumberSettingState.getInstance()

    private val fileEditorManagerEx: FileEditorManagerEx = FileEditorManagerEx.getInstanceEx(project)

    private lateinit var editorWindow: EditorWindow

    private lateinit var openedJBTabs: JBTabs

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        refreshTabNumber()
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        refreshTabNumber()
    }

    private fun refreshTabNumber() {
        if (!this::editorWindow.isInitialized || !this::openedJBTabs.isInitialized) {
            val currentWindow = fileEditorManagerEx.currentWindow ?: return
            editorWindow = currentWindow
            val tabbedPane = editorWindow.tabbedPane
            openedJBTabs = tabbedPane.tabs
            openedJBTabs.addListener(object : TabsListener {
                override fun tabsMoved() {
                    refreshTabNumber()
                }
            })
        }

        val files: Array<VirtualFile> = editorWindow.files
        for (index in files.indices) {
            openedJBTabs.getTabAt(index).text = (index + 1).toString() + tabNumberSettingState.tabNumberSeparator + files[index].presentableName
        }
    }
}
