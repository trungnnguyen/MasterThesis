package gui;

import logic.CmdOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by Filip on 2016-09-18.
 */
public class Actions {
    private static final String resourcesPath = "E:\\github\\MasterThesis-Designer";
    private static final Dimension windowSize = new Dimension(MainWindowDialog.getWIDTH(), MainWindowDialog.getHEIGHT());
    private static final Dimension increasedWindowSize = new Dimension(MainWindowDialog.getWIDTH(), MainWindowDialog.getHEIGHT() + 100);

    public static ActionListener startCAEAction() {
        return e -> {
            try {
                CmdOperations.startCAE();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }

    public static ActionListener setScriptOptionsVisibility(JCheckBox withScriptCheckBox, JRadioButton chooseScriptFileRB, JRadioButton writeScriptRB) {
        return e -> {
            boolean isSet = withScriptCheckBox.isSelected();
            chooseScriptFileRB.setEnabled(isSet);
            writeScriptRB.setEnabled(isSet);
        };
    }

    public static ActionListener startCAEWithScriptAction(MainWindowDialog mainWindow) {
        return new ActionListener() {
            JFileChooser jfc = new JFileChooser();
            String pathToScript = null;

            public void actionPerformed(ActionEvent event) {
                jfc.setCurrentDirectory(new File(resourcesPath));
                int returnVal = jfc.showDialog(mainWindow.getContentPane(), "Choose");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getSelectedFile().getAbsolutePath());
                    pathToScript = jfc.getSelectedFile().getAbsolutePath();
                }
                try {
                    CmdOperations.startCAEWithScript(pathToScript);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
    }

    public static ActionListener writeOwnScript(JTextArea jTextArea) {
        return e -> jTextArea.setVisible(true);
    }

    public static ActionListener chooseWriteScript(JButton chooseScriptFileButton, JButton writeYourScriptButton, JTextArea scriptArea) {
       return e -> {
           MainWindowDialog mainWindow = MainWindowDialog.getInstance();
           chooseScriptFileButton.setEnabled(false);
           writeYourScriptButton.setEnabled(true);
           scriptArea.setVisible(true);

           MainWindowDialog.getInstance().setSize(increasedWindowSize);
       };
    }

    public static ActionListener chooseScriptFromFile(JButton chooseScriptFileButton, JButton writeYourScriptButton, JTextArea scriptArea) {
        return e -> {
            MainWindowDialog mainWindow = MainWindowDialog.getInstance();
           // if(mainWindow.getWriteScriptRB().isSelected()) {
                scriptArea.setVisible(false);
                mainWindow.setSize(windowSize);
           // }
            chooseScriptFileButton.setEnabled(true);
            writeYourScriptButton.setEnabled(false);
        };
    }

    public static ActionListener runWrittenScript(JTextArea scriptText, String pathToCAE) {
        return e -> {
            try {
                File script = new File("tmpScript.py");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(script));
                bufferedWriter.append(scriptText.getText());
                bufferedWriter.flush();
                CmdOperations.startCAEWithScript(script.getAbsolutePath());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }
}
