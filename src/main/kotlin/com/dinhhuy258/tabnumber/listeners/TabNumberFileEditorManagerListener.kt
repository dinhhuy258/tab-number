package com.dinhhuy258.tabnumber.listeners

import com.dinhhuy258.tabnumber.settings.TabNumberSettingState
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.tabs.JBTabs
import com.intellij.ui.tabs.TabInfo
import com.intellij.ui.tabs.TabsListener

class TabNumberFileEditorManagerListener(project: Project) : FileEditorManagerListener {
    private val tabNumberSettingState: TabNumberSettingState = TabNumberSettingState.getInstance()

    private val fileEditorManagerEx: FileEditorManagerEx = FileEditorManagerEx.getInstanceEx(project)

    private lateinit var editorWindow: EditorWindow

    private lateinit var openedJBTabs: JBTabs

    private val log: Logger = Logger.getInstance("TabNumber")

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        refreshTabNumber()
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        refreshTabNumber()
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        super.selectionChanged(event)
        refreshTabNumber()
    }

    private fun refreshTabNumber() {
        if (!this::editorWindow.isInitialized || !this::openedJBTabs.isInitialized) {
            val currentWindow = fileEditorManagerEx.currentWindow ?: return
            editorWindow = currentWindow
            val tabbedPane = editorWindow.tabbedPane ?: return
            openedJBTabs = tabbedPane.tabs
            openedJBTabs.addListener(object : TabsListener {
                override fun tabsMoved() {
                    refreshTabNumber()
                }

                override fun selectionChanged(oldSelection: TabInfo?, newSelection: TabInfo?) {
                    super.selectionChanged(oldSelection, newSelection)
                    refreshTabNumber()
                }

                override fun tabRemoved(tabToRemove: TabInfo) {
                    super.tabRemoved(tabToRemove)
                    refreshTabNumber()
                }
            })
        }

        //todo:解决多编辑器下，另外的编辑器中不展示tab number的问题。原因是因为当前的openedJBTabs仅为第一个编辑器
        val files: Array<VirtualFile> = editorWindow.files
        for (index in files.indices) {
            openedJBTabs.getTabAt(index).text = (index + 1).toString() + tabNumberSettingState.tabNumberSeparator + files[index].presentableName
        }
    }
}
