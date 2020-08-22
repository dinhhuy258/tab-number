package com.dinhhuy258.tabnumber.providers

import com.dinhhuy258.tabnumber.constants.TabNumberConstants
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.annotations.Nullable

class TabNumberEditorTabTitleProvider : EditorTabTitleProvider {
    @Nullable
    override fun getEditorTabTitle(project: Project, file: VirtualFile): String? {
        val fileEditorManagerEx = FileEditorManagerEx.getInstanceEx(project)
        val currentWindow: EditorWindow = fileEditorManagerEx.currentWindow
        val files = currentWindow.files
        for (index in files.indices) {
            if (files[index] == file) {
                return (index + 1).toString() + TabNumberConstants.TAB_DELIMITER + file.presentableName
            }
        }

        return null
    }
}
