package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Filip on 2016-11-12.
 */

public class Application {

    public static void main(String[] args) {

        MainWindowDialog mainWindowDialog = MainWindowDialog.getInstance();
        mainWindowDialog.pack();
        mainWindowDialog.setVisible(true);

    }

}
