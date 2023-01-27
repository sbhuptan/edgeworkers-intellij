package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.jcef.JBCefApp;
import org.jetbrains.annotations.NotNull;

public class CodeProfilerToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        CodeProfilerToolWindow codeProfilerToolWindow = new CodeProfilerToolWindow();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        try {
            Content content = contentFactory.createContent(codeProfilerToolWindow.getContent(), "", false);
            toolWindow.getContentManager().addContent(content);
            if (!JBCefApp.isSupported()) {
                Messages.showWarningDialog(
                        "JCEF is not supported in this environment. Profiling results will not be displayed within the IDE. Please " +
                                "<a href=\"https://plugins.jetbrains.com/docs/intellij/jcef.html#enabling-jcef\">enable JCEF</a>" +
                                " to view profiling results within the IDE.",
                        "JCEF Unsupported");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}