package io;

import constants.MeleeConstants;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UnlockablesSaver {

    public void saveUnlockablesToFile(ArrayList<JCheckBox> unlockableStatus) {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter( new FileOutputStream("unlockables.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("File does not exist");
            System.exit(0);
        }

        for (int i = 0; i< MeleeConstants.UNLOCKABLES.length; i++) {
            outputStream.println(MeleeConstants.UNLOCKABLES[i] + "=" + unlockableStatus.get(i).isSelected());
        }

        outputStream.close();
    }
}
