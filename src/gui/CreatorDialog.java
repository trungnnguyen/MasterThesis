package gui;

import commands.Commands;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CreatorDialog extends JDialog {
    private JPanel contentPane;
    private JButton createPartButton;
    private JButton createSqueezeProcess;
    private JButton runCAEButton;
    private JButton steelButton;
    private JButton defaultsMeshInstancesAndButton;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;
    private PrintWriter out;

    private static class MyWrapper {
        static CreatorDialog INSTANCE = new CreatorDialog();
    }

    private CreatorDialog() {
        setContentPane(contentPane);
        setModal(true);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        File script = initializeScriptFile();

        createPartButton.addActionListener(Actions.appendScript(script, Commands.getCreateCube()));
        steelButton.addActionListener(Actions.appendScript(script, Commands.getCreateMaterialSteel()));
        defaultsMeshInstancesAndButton.addActionListener(Actions.addDefaults(script));
        createSqueezeProcess.addActionListener(Actions.createSqueezeProcess(script));
        runCAEButton.addActionListener(Actions.saveStartCaeWithScript(script));

    }

    public static CreatorDialog getInstance() {
        return CreatorDialog.MyWrapper.INSTANCE;
    }

    private File initializeScriptFile() {
        File script = new File("resources/script.py");

        try {
            if (!script.exists()) {
                script.createNewFile();
            } else {
                new PrintWriter(script.getAbsolutePath()).close();
            }
            Files.write(Paths.get(script.getPath()), Commands.getImports().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Script initialized");
        return script;
    }

}
