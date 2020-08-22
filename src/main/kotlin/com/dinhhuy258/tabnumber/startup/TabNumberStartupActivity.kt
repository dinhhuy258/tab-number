package com.dinhhuy258.tabnumber.startup

import com.dinhhuy258.tabnumber.listeners.TabNumberFileEditorManagerListener
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class TabNumberStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        val messageBus = project.messageBus
        val messageBusConnection = messageBus.connect()
        messageBusConnection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, TabNumberFileEditorManagerListener(project))
    }
}
