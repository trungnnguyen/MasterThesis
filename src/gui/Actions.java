package gui;

import commands.CommandsFactory;
import commands.TubeCommands;
import logic.CmdOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Created by Filip on 2016-09-18.
 */
public class Actions {
    private static final String resourcesPath = "E:\\github\\MasterThesis-Designer";
    private static final String modelPath = "C:/IdeaProjects/MasterThesis-Designer/resources/test.cae";
    private static final Dimension windowSize = new Dimension(MainWindowDialog.getWIDTH() + 20, MainWindowDialog.getHEIGHT() + 20);
    private static final Dimension increasedWindowSize = new Dimension(MainWindowDialog.getWIDTH(), MainWindowDialog.getHEIGHT() + 100);

    public static ActionListener startCae() {
        return e -> {
            try {
                CmdOperations.startCAE();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }

    public static ActionListener saveStartCaeWithScript(File script) {
        return e -> {
            try {
                String scriptPath = script.getAbsolutePath();
                //delete existing model
                if (Files.exists(Paths.get(resourcesPath)))
                    Files.delete(Paths.get(resourcesPath));

                Files.write(Paths.get(scriptPath), CommandsFactory.getSave().getBytes(), StandardOpenOption.APPEND);
                CmdOperations.startCAEWithScript(script);
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

    public static ActionListener startCaeWithChoosenScriptAction(MainWindowDialog mainWindow) {
        return new ActionListener() {
            JFileChooser jfc = new JFileChooser();
            File pathToScript = null;

            public void actionPerformed(ActionEvent event) {
                jfc.setCurrentDirectory(new File(resourcesPath));
                int returnVal = jfc.showDialog(mainWindow.getContentPane(), "Choose");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getSelectedFile().getAbsolutePath());
                    pathToScript = jfc.getSelectedFile();
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

    public static ActionListener runWrittenScript(String scriptContent) {
        return e -> {
            try {
                File script = new File("tmpScript.py");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(script));
                bufferedWriter.append(scriptContent);
                bufferedWriter.flush();
                CmdOperations.startCAEWithScript(script);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }

    public static ActionListener goToCreator(JButton creatorButton) {
        return e -> {

            MainWindowDialog.getInstance().setVisible(false);

            JDialog dialog = CreatorDialog.getInstance();
            dialog.setModal(true);
            dialog.pack();
            dialog.setVisible(true);

        };
    }

    public static ActionListener appendScript(File script, String command) {
        return e -> {
            try {
                Files.write(Paths.get(script.getPath()), command.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }

    public static ActionListener createSqueezeProcess(File script) {
        String command = CommandsFactory.getCreateStep() + CommandsFactory.getCreateBottomFix()
                + CommandsFactory.getCreateTopDisplacement() + CommandsFactory.getApplyMesh() + CommandsFactory.getCreateJob();
        return appendScript(script, command);
    }

    public static ActionListener createExtentionProcess(File script) {
        String command = CommandsFactory.getCreateStep() + CommandsFactory.getCreateBottomFix()
                + CommandsFactory.getTopExtention() + CommandsFactory.getApplyMesh() + CommandsFactory.getCreateJob();
        return appendScript(script, command);
    }

    public static ActionListener createSteel(File script) {
        String command = CommandsFactory.getCreateSteel() + CommandsFactory.getCreateSteelSection() + CommandsFactory.getAssignSection()
                + CommandsFactory.getCreateInstance();
        return appendScript(script, command);
    }

    public static ActionListener createAluminium(File script) {
        String command = CommandsFactory.getCreateAluminium() + CommandsFactory.getCreateAluminiumSection() + CommandsFactory.getAssignSection()
                + CommandsFactory.getCreateInstance();
        return appendScript(script, command);
    }
}
