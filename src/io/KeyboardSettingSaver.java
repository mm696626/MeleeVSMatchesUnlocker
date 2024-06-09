package io;

import constants.MeleeConstants;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KeyboardSettingSaver {

    public void saveKeyboardSettingsToFile(ArrayList<JComboBox> keyboardSettings) {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter( new FileOutputStream("keyboardSettings.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("File does not exist");
            System.exit(0);
        }

        for (int i = 0; i<keyboardSettings.size(); i++) {
            outputStream.println(MeleeConstants.GAMECUBE_BUTTONS[i] + "=" + MeleeConstants.KEYBOARD_KEYS[keyboardSettings.get(i).getSelectedIndex()]);
        }

        outputStream.close();
    }
}
