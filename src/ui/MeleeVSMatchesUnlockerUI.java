package ui;

import constants.MeleeConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MeleeVSMatchesUnlockerUI extends JFrame implements ActionListener {


    private JButton startUnlocking, stopUnlocking;
    private ArrayList<JLabel> buttonLabels;
    private ArrayList<JLabel> unlockableLabels;
    private ArrayList<JComboBox> buttonAssignments;
    private ArrayList<JCheckBox> earnedUnlockables;
    private int vsMatches;
    private boolean isUnlocking;
    private MeleeUnlockProgressUI meleeUnlockProgressUI;


    public MeleeVSMatchesUnlockerUI()
    {
        setTitle("Melee VS Matches Unlocker");
        generateUI();
    }

    private void generateUI() {

        buttonLabels = new ArrayList<>();
        buttonAssignments = new ArrayList<>();
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

        stopUnlocking = new JButton("Stop Unlocking");
        stopUnlocking.addActionListener(this);
        mainMenuPanel.add(stopUnlocking);


        for (int i=0; i<MeleeConstants.GAMECUBE_BUTTONS.length; i++) {
            JLabel jLabel = new JLabel(MeleeConstants.GAMECUBE_BUTTONS[i]);
            buttonLabels.add(jLabel);
            keyboardSettingsPanel.add(buttonLabels.get(i));
            JComboBox jComboBox = new JComboBox(MeleeConstants.KEYBOARD_KEYS);
            buttonAssignments.add(jComboBox);
            keyboardSettingsPanel.add(jComboBox);
        }

        for (int i=0; i<MeleeConstants.UNLOCKABLES.length; i++) {
            JLabel jLabel = new JLabel(MeleeConstants.UNLOCKABLES[i]);
            unlockableLabels.add(jLabel);
            unlockablesPanel.add(unlockableLabels.get(i));
            JCheckBox jCheckBox = new JCheckBox();
            earnedUnlockables.add(jCheckBox);
            unlockablesPanel.add(earnedUnlockables.get(i));
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Toggle Unlocker", mainMenuPanel);
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
                    isValidResponse = true;
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "That was not a valid response! Please try again!");
                }
            }



            isUnlocking = true;
            try {
                meleeUnlockProgressUI = new MeleeUnlockProgressUI(vsMatches);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            meleeUnlockProgressUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            meleeUnlockProgressUI.pack();
            meleeUnlockProgressUI.setVisible(true);
        }

        if (e.getSource() == stopUnlocking) {
            if (isUnlocking) {
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(this,  "Unlocking isn't active!");
            }
        }

    }
}