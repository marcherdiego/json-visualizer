package com.marcherdiego.json.visualizer;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

class ViewJsonAction extends AnAction {
   @Override
   public void actionPerformed(@NotNull AnActionEvent actionEvent) {
      Project project = getEventProject(actionEvent);
      if (project == null) {
         return;
      }
      VirtualFile selectedFile = actionEvent.getData(CommonDataKeys.PSI_FILE).getVirtualFile();
      JsonParser.INSTANCE.parse(project, selectedFile);
   }
}
