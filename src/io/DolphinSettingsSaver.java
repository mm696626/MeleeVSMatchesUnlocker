package io;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DolphinSettingsSaver {

    public void saveDolphinSettingsToFile(ArrayList<JLabel> dolphinSettings, ArrayList<JCheckBox> dolphinSettingsToggles) {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter( new FileOutputStream("dolphinSettings.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("File does not exist");
            System.exit(0);
        }

        for (int i = 0; i<dolphinSettingsToggles.size(); i++) {
            outputStream.println(dolphinSettings.get(i).getText() + "=" + dolphinSettingsToggles.get(i).isSelected());
        }

        outputStream.close();
    }
}
