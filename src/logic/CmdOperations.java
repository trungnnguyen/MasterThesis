package logic;

import gui.MainWindowDialog;

import java.io.IOException;

/**
 * Created by Filip on 2016-09-18.
 */
public class CmdOperations {
    static Runtime runtime;

    private final static String CAE = " cae";
    private final static String startCAEWithScript = "c:\\SIMULIA\\Abaqus\\Commands\\abaqus.bat cae script=";

    public static void startCAEWithScript(String pathToScript) throws IOException {
        StringBuilder command = new StringBuilder();
        command.append(startCAEWithScript);
        command.append(pathToScript);
        System.out.println(command);
        Runtime.getRuntime().exec(command.toString());
    }

    public static void startCAE() throws IOException {
        MainWindowDialog mainWindowDialog = MainWindowDialog.getInstance();
        String path = mainWindowDialog.getTxtPath();
        Runtime.getRuntime().exec(path + CAE);
    }

    public static void startCMD() throws IOException {
        Runtime.getRuntime().exec("cmd.exe /c start");
    }
}
