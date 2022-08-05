package com.marcherdiego.json.visualizer

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerInfo.LineMarkerGutterIconRenderer
import com.intellij.codeInspection.ProblemHighlightType.INFORMATION
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiElement

internal class LineMarker : Annotator {
    private var matched = false

    override fun annotate(psiElement: PsiElement, holder: AnnotationHolder) {
        val elementContainingFile = psiElement.containingFile
        val fileText = elementContainingFile.text
        val elementLine = StringUtil.offsetToLineNumber(fileText, psiElement.textOffset)
        if (elementLine != 0) {
            return
        }
        if (matched) {
            return
        }
        matched = true
        val tooltip = "Click here to view the JSON Diagram"
        val lineMarkerInfo = LineMarkerInfo(
            psiElement, psiElement.textRange,
            postersIcon, { tooltip },
            { _, _ ->
                JsonParser.parse(psiElement.project, fileText)
            },
            GutterIconRenderer.Alignment.CENTER, { tooltip }
        )
        holder
            .newAnnotation(HighlightSeverity.INFORMATION, tooltip)
            .highlightType(INFORMATION)
            .gutterIconRenderer(LineMarkerGutterIconRenderer(lineMarkerInfo))
            .create()
    }

    companion object {
        @JvmField
        val postersIcon = IconLoader.getIcon("/icons/Json(Color).svg", LineMarker::class.java)
    }
}
