package com.marcherdiego.json.visualizer

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.security.SFile
import java.io.File
import java.io.IOException
import java.util.Timer
import kotlin.concurrent.schedule

object JsonParser {
    fun parse(project: Project, file: VirtualFile) {
        try {
            val input = LocalFileSystem.getInstance().getInputStream(file).readBytes().toString(Charsets.UTF_8)
            parse(project, input)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun parse(project: Project, input: String) {
        val outputFile = File.createTempFile("temp_json", ".png")
        val inputString = StringBuilder()
            .append("@startjson\n")
            .append(input)
            .append("\n@endjson")
        SourceStringReader(inputString.toString())
            .outputImage(
                SFile.fromFile(outputFile)
            )
        LocalFileSystem.getInstance().refresh(false)
        val virtualFile = LocalFileSystem.getInstance().findFileByIoFile(outputFile) ?: return
        FileEditorManager.getInstance(project).openFileEditor(OpenFileDescriptor(project, virtualFile), true)
        Timer().schedule(1000L) {
            outputFile.deleteOnExit()
        }
    }
}
