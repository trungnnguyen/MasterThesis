package logic;

import gui.MainWindowDialog;

import java.io.File;
import java.io.IOException;

/**
 * Created by Filip on 2016-09-18.
 */
public class CmdOperations {
    static Runtime runtime;

    private final static String cae = "c:\\SIMULIA\\Abaqus\\Commands\\abaqus.bat cae";
    private final static String caeNoGui = "c:\\SIMULIA\\Abaqus\\Commands\\abaqus.bat cae";
    private final static String startCaeWithScript = "c:\\SIMULIA\\Abaqus\\Commands\\abaqus.bat cae script=";

    public static void startCAEWithScript(File script) throws IOException {
        StringBuilder command = new StringBuilder();
        command.append(startCaeWithScript);
        command.append(script.getAbsolutePath());
        System.out.println(command);
        Runtime.getRuntime().exec(command.toString());
    }

    public static void startCAE() throws IOException {
        MainWindowDialog mainWindowDialog = MainWindowDialog.getInstance();
        Runtime.getRuntime().exec(cae);
    }

    public static void startCMD() throws IOException {
        Runtime.getRuntime().exec("cmd.exe /c start");
    }

    public static void startCaeNoGui() throws IOException {
        MainWindowDialog mainWindowDialog = MainWindowDialog.getInstance();
        Runtime.getRuntime().exec(caeNoGui);
    }
}
