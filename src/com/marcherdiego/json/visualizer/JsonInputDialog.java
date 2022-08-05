package com.marcherdiego.json.visualizer;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.intellij.openapi.project.Project;

public class JsonInputDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea json;
    private final Project project;

    public JsonInputDialog(Project project) {
        this.project = project;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> parseJson());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void parseJson() {
        String input = json.getText();
        JsonParser.INSTANCE.parse(project, input);
    }

    private void onCancel() {
        dispose();
    }
}
