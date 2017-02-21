package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindowDialog extends JDialog {

    private static final int HEIGHT = 180;
    private static final int WIDTH = 800;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton runCAE;
    private JCheckBox withScriptCheckBox;
    private JButton chooseScriptFileButton;
    private JButton writeYourScriptButton;
    private JTextArea writeScriptArea;

    private JRadioButton chooseScriptFileRB;
    private JRadioButton writeScriptRB;
    private JButton creatorButton;

    private static class MyWrapper {
        static MainWindowDialog INSTANCE = new MainWindowDialog();
    }

    private MainWindowDialog() {
        setContentPane(contentPane);
        setModal(true);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        chooseScriptFileButton.setEnabled(false);
        writeYourScriptButton.setEnabled(false);
        chooseScriptFileRB.setEnabled(false);
        writeScriptRB.setEnabled(false);
        writeScriptArea.setVisible(false);
        runCAE.addActionListener(Actions.startCae());
        chooseScriptFileButton.addActionListener(Actions.startCaeWithChoosenScriptAction(this));
        withScriptCheckBox.addActionListener(Actions.setScriptOptionsVisibility(withScriptCheckBox, chooseScriptFileRB, writeScriptRB));
        writeYourScriptButton.addActionListener(Actions.writeOwnScript(writeScriptArea));
        writeScriptRB.addActionListener(Actions.chooseWriteScript(chooseScriptFileButton, writeYourScriptButton, writeScriptArea));
        chooseScriptFileRB.addActionListener(Actions.chooseScriptFromFile(chooseScriptFileButton, writeYourScriptButton, writeScriptArea));
        writeYourScriptButton.addActionListener(Actions.runWrittenScript(writeScriptArea.getText()));
        creatorButton.addActionListener(Actions.goToCreator(creatorButton));

    }

    public static MainWindowDialog getInstance() {
        return MyWrapper.INSTANCE;
    }

    public JRadioButton getChooseScriptFileRB() {
        return chooseScriptFileRB;
    }

    public JRadioButton getWriteScriptRB() {
        return writeScriptRB;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

}
