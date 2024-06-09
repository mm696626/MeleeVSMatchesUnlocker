package ui;

import constants.MeleeConstants;
import io.KeyboardSettingSaver;
import io.UnlockablesSaver;
import unlocking.MeleeUnlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MeleeVSMatchesUnlockerUI extends JFrame implements ActionListener {


    private JButton startUnlocking;
    private ArrayList<JLabel> buttonLabels;
    private ArrayList<JLabel> unlockableLabels;
    private ArrayList<JComboBox> keyboardButtonAssignments;
    private ArrayList<JCheckBox> earnedUnlockables;
    private int vsMatches;
    private MeleeUnlocker meleeUnlocker;


    public MeleeVSMatchesUnlockerUI()
    {
        setTitle("Melee VS Matches Unlocker");
        generateUI();

        File keyboardSettings = new File("keyboardSettings.txt");
        if (keyboardSettings.exists()) {
            loadKeyboardSettingsOnStartUp();
        }

        File unlockables = new File("unlockables.txt");
        if (unlockables.exists()) {
            loadUnlockablesSettingsOnStartUp();
        }
    }

    private void generateUI() {

        buttonLabels = new ArrayList<>();
        keyboardButtonAssignments = new ArrayList<>();
        unlockableLabels = new ArrayList<>();
        earnedUnlockables = new ArrayList<>();

        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(1, 2);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        JPanel keyboardSettingsPanel = new JPanel();
        GridLayout keyboardSettingsGridLayout = new GridLayout(MeleeConstants.GAMECUBE_BUTTONS.length,2);
        keyboardSettingsPanel.setLayout(keyboardSettingsGridLayout);

        JPanel unlockablesPanel = new JPanel();
        GridLayout unlockablesGridLayout = new GridLayout(MeleeConstants.UNLOCKABLES.length,2);
        unlockablesPanel.setLayout(unlockablesGridLayout);

        startUnlocking = new JButton("Start Unlocking");
        startUnlocking.addActionListener(this);
        mainMenuPanel.add(startUnlocking);

        for (int i=0; i<MeleeConstants.GAMECUBE_BUTTONS.length; i++) {
            JLabel jLabel = new JLabel(MeleeConstants.GAMECUBE_BUTTONS[i]);
            buttonLabels.add(jLabel);
            keyboardSettingsPanel.add(buttonLabels.get(i));
            JComboBox jComboBox = new JComboBox(MeleeConstants.KEYBOARD_KEYS);
            jComboBox.setSelectedIndex(MeleeConstants.DEFAULT_SETTINGS[i]);
            KeyboardSettingSaver keyboardSettingSaver = new KeyboardSettingSaver();
            jComboBox.addActionListener(e -> keyboardSettingSaver.saveKeyboardSettingsToFile(keyboardButtonAssignments));
            keyboardButtonAssignments.add(jComboBox);
            keyboardSettingsPanel.add(jComboBox);
        }

        for (int i=0; i<MeleeConstants.UNLOCKABLES.length; i++) {
            JLabel jLabel = new JLabel(MeleeConstants.UNLOCKABLES[i]);
            unlockableLabels.add(jLabel);
            unlockablesPanel.add(unlockableLabels.get(i));
            JCheckBox jCheckBox = new JCheckBox();
            earnedUnlockables.add(jCheckBox);


            unlockablesPanel.add(earnedUnlockables.get(i));

            UnlockablesSaver unlockablesSaver = new UnlockablesSaver();
            earnedUnlockables.get(i).addActionListener(e -> unlockablesSaver.saveUnlockablesToFile(earnedUnlockables));
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Start Unlocker", mainMenuPanel);
        tabbedPane.add("Keyboard Settings", keyboardSettingsPanel);
        tabbedPane.add("Unlockables", unlockablesPanel);
        add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startUnlocking) {

            boolean isValidResponse = false;
            while (!isValidResponse) {
                String response = JOptionPane.showInputDialog(this, "How many VS matches have you done on your save file?");
                try {
                    vsMatches = Integer.parseInt(response);

                    if (vsMatches >= 0) {
                        isValidResponse = true;
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "That was not a valid response! Please try again!");
                }
            }

            int vsMatchesTarget = determineVsMatchesTarget();
            int[] buttonAssignments = getKeyboardButtonAssignments();

            //no point in unlocking if vs match target is too low
            if (vsMatchesTarget < vsMatches) {
                JOptionPane.showMessageDialog(this, "Invalid VS match count for unlockables you have!");
                return;
            }

            try {
                meleeUnlocker = new MeleeUnlocker(vsMatches, vsMatchesTarget, buttonAssignments);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            meleeUnlocker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            meleeUnlocker.pack();
            meleeUnlocker.setVisible(true);
        }
    }

    private int determineVsMatchesTarget() {
        for (int i=0; i<earnedUnlockables.size(); i++) {
            if (!earnedUnlockables.get(i).isSelected()) {
                return MeleeConstants.VS_MATCHES_REQUIRED[i];
            }
        }

        return -1;
    }

    private int[] getKeyboardButtonAssignments() {
        int[] buttonAssignments = new int[MeleeConstants.GAMECUBE_BUTTONS.length];

        for (int i=0; i<keyboardButtonAssignments.size(); i++) {
            buttonAssignments[i] = MeleeConstants.KEYBOARD_KEY_CODES[keyboardButtonAssignments.get(i).getSelectedIndex()];
        }

        return buttonAssignments;
    }

    private void loadKeyboardSettingsOnStartUp() {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream("keyboardSettings.txt"));
        } catch (FileNotFoundException e) {
            return;
        }

        int index = 0;

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();

            String settingValue = line.split("=")[1];

            for (int i=0; i<MeleeConstants.KEYBOARD_KEYS.length; i++) {
                if (MeleeConstants.KEYBOARD_KEYS[i].equals(settingValue)) {
                    keyboardButtonAssignments.get(index).setSelectedIndex(i);
                    break;
                }
            }

            index++;
        }

        inputStream.close();
    }

    private void loadUnlockablesSettingsOnStartUp() {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream("unlockables.txt"));
        } catch (FileNotFoundException e) {
            return;
        }

        int index = 0;

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            String settingValue = line.split("=")[1];
            boolean isSelected = Boolean.parseBoolean(settingValue);
            earnedUnlockables.get(index).setSelected(isSelected);
            index++;
        }

        inputStream.close();
    }
}