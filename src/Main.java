// Melee VS Matches Unlocker by Matt McCullough
// This is to automate unlocking things that require VS matches

import ui.MeleeVSMatchesUnlockerUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MeleeVSMatchesUnlockerUI meleeVSMatchesUnlockerUI = new MeleeVSMatchesUnlockerUI();
        meleeVSMatchesUnlockerUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meleeVSMatchesUnlockerUI.pack();
        meleeVSMatchesUnlockerUI.setVisible(true);
    }
}