package ui;

import constants.MeleeConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MeleeVSMatchesUnlockerUI extends JFrame implements ActionListener {


    //where to grab iso or ciso files from
    private JButton startUnlocking, stopUnlocking;
    private boolean isUnlocking;
    private ArrayList<JLabel> buttonLabels;
    private ArrayList<JComboBox> buttonAssignments;


    public MeleeVSMatchesUnlockerUI()
    {
        setTitle("Melee VS Matches Unlocker");
        generateUI();
    }

    private void generateUI() {

        buttonLabels = new ArrayList<>();
        buttonAssignments = new ArrayList<>();

        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(1, 2);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        JPanel keyboardSettingsPanel = new JPanel();
        GridLayout keyboardSettingsGridLayout = new GridLayout(MeleeConstants.GAMECUBE_BUTTONS.length,2);
        keyboardSettingsPanel.setLayout(keyboardSettingsGridLayout);

        startUnlocking = new JButton("Start Unlocking");
        startUnlocking.addActionListener(this);
        mainMenuPanel.add(startUnlocking);

        stopUnlocking = new JButton("Stop Unlocking");
        stopUnlocking.addActionListener(this);
        mainMenuPanel.add(stopUnlocking);


        for (int i=0; i< MeleeConstants.GAMECUBE_BUTTONS.length; i++) {
            JLabel jLabel = new JLabel(MeleeConstants.GAMECUBE_BUTTONS[i]);
            buttonLabels.add(jLabel);
            keyboardSettingsPanel.add(buttonLabels.get(i));
            JComboBox jComboBox = new JComboBox(MeleeConstants.KEYBOARD_KEYS);
            buttonAssignments.add(jComboBox);
            keyboardSettingsPanel.add(jComboBox);
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Generate Folder", mainMenuPanel);
        tabbedPane.add("Keyboard Settings", keyboardSettingsPanel);
        add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}