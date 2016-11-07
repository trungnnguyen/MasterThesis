package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowDialog extends JDialog {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 800;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblPath;
    private JTextField txtPath;
    private JButton runCAE;
    private JCheckBox withScriptCheckBox;
    private JButton chooseScriptFileButton;
    private JButton writeYourScriptButton;
    private JTextArea writeScriptArea;

    private JRadioButton chooseScriptFileRB;
    private JRadioButton writeScriptRB;

    private static class MyWrapper {
        static MainWindowDialog INSTANCE = new MainWindowDialog();
    }

    private MainWindowDialog() {
        setContentPane(contentPane);
        setModal(true);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        txtPath.setMinimumSize(new Dimension(250, 10));
        chooseScriptFileButton.setEnabled(false);
        writeYourScriptButton.setEnabled(false);
        chooseScriptFileRB.setEnabled(false);
        writeScriptRB.setEnabled(false);
        writeScriptArea.setVisible(false);
        runCAE.addActionListener(Actions.startCAEAction());
        chooseScriptFileButton.addActionListener(Actions.startCAEWithScriptAction(this));
        withScriptCheckBox.addActionListener(Actions.setScriptOptionsVisibility(withScriptCheckBox, chooseScriptFileRB, writeScriptRB));
        writeYourScriptButton.addActionListener(Actions.writeOwnScript(writeScriptArea));
        writeScriptRB.addActionListener(Actions.chooseWriteScript(chooseScriptFileButton, writeYourScriptButton, writeScriptArea));
        chooseScriptFileRB.addActionListener(Actions.chooseScriptFromFile(chooseScriptFileButton, writeYourScriptButton, writeScriptArea));
        writeYourScriptButton.addActionListener(Actions.runWrittenScript(writeScriptArea, txtPath.getText()));
    }

    public static MainWindowDialog getInstance() {
        return MyWrapper.INSTANCE;
    }

    public static void main(String[] args) {
        MainWindowDialog dialog = getInstance();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getTxtPath() {
        return txtPath.getText();
    }

    public void openTextArea() {

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
