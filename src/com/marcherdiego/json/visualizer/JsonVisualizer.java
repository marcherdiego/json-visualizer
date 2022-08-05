package com.marcherdiego.json.visualizer;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;

public class JsonVisualizer extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent actionEvent) {
        Project project = getEventProject(actionEvent);
        if (project == null) {
            return;
        }
        JsonInputDialog jsonInputDialog = new JsonInputDialog(project);
        jsonInputDialog.pack();
        jsonInputDialog.setLocationRelativeTo(null);
        jsonInputDialog.setTitle("Enter JSON to parse...");
        jsonInputDialog.setResizable(true);
        jsonInputDialog.setVisible(true);
    }

    @Override
    public void update(final AnActionEvent actionEvent) {
        final Project project = actionEvent.getData(CommonDataKeys.PROJECT);
        actionEvent.getPresentation().setVisible(project != null);
    }
}
