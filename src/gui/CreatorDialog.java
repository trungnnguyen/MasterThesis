package gui;

import commands.CommandsFactory;
import commands.CubeCommands;
import commands.TubeCommands;

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
    private JButton tubeButton;
    private JButton aluminiumButton;
    private JButton extendButton;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private static class MyWrapper {
        static CreatorDialog INSTANCE = new CreatorDialog();
    }

    private CreatorDialog() {
        setContentPane(contentPane);
        setModal(true);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        File script = initializeScriptFile();

        createPartButton.addActionListener(Actions.appendScript(script, CubeCommands.getCreateCube()));
        steelButton.addActionListener(Actions.createSteel(script));
        createSqueezeProcess.addActionListener(Actions.createSqueezeProcess(script));

        tubeButton.addActionListener(Actions.appendScript(script, TubeCommands.getCreateTube()));
        aluminiumButton.addActionListener(Actions.createAluminium(script));
        extendButton.addActionListener(Actions.createExtentionProcess(script));


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
            Files.write(Paths.get(script.getPath()), CommandsFactory.getImports().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Script initialized");
        return script;
    }

}
