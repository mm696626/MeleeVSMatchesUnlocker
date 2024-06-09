package unlocking;

import constants.ButtonConstants;

import javax.swing.*;
import java.awt.*;

public class MeleeUnlocker extends JFrame {


    private int vsMatches;
    private int vsMatchesTarget;

    private int[] buttonAssignments;



    public MeleeUnlocker(int vsMatches, int vsMatchesTarget, int[] buttonAssignments) throws InterruptedException, AWTException {
        this.vsMatches = vsMatches;
        this.vsMatchesTarget = vsMatchesTarget;
        this.buttonAssignments = buttonAssignments;
        unlock(buttonAssignments);
    }

    private void unlock(int[] buttonAssignments) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        JOptionPane.showMessageDialog(this, "Found unlockable at " + vsMatchesTarget + " VS matches!" + " Boot Melee and click into your window within 2 seconds of closing this box");
        Thread.sleep(2000);


        //START
        for (int i=0; i<2; i++) {
            pressKey(robot, 1000, buttonAssignments[ButtonConstants.START]);
        }

        //simulate load time
        simulateLoadTime(3000);

        //press down to go to Melee mode
        pressKey(robot, 100, buttonAssignments[ButtonConstants.DOWN_ON_STICK]);
        simulateLoadTime(100);

        //press A twice
        for (int i=0; i<2; i++) {
            pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);
        }

        //simulate loading time
        simulateLoadTime(5000);

        //Pick Fox
        pressKey(robot, 100, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 450, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 0, buttonAssignments[ButtonConstants.A]);

        //up and right
        pressKey(robot, 200, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);

        //go to rules
        pressKey(robot, 0, buttonAssignments[ButtonConstants.A]);

        simulateLoadTime(500);

        //press right to change to stock
        pressKey(robot, 0, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);

        simulateLoadTime(1000);

        pressKey(robot, 0, buttonAssignments[ButtonConstants.DOWN_ON_STICK]);

        pressKey(robot, 0, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        pressKey(robot, 0, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        pressKey(robot, 0, buttonAssignments[ButtonConstants.B]);


        //Turn on a CPU
        pressKey(robot, 180, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 250, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 0, buttonAssignments[ButtonConstants.A]);

        simulateLoadTime(100);

        for (int i=vsMatches; i<vsMatchesTarget; i++) {
            doVSMatch(robot);
            simulateLoadTime(4000);
            pressKey(robot, 100, buttonAssignments[ButtonConstants.START]);
        }

        pressKey(robot, 100, buttonAssignments[ButtonConstants.PAUSE_HOTKEY]);
        JOptionPane.showMessageDialog(this, "Unlock target reached! Pausing game. Press your resume hotkey to get your unlockable!");
        setVisible(false);
    }

    private void doVSMatch(Robot robot) throws InterruptedException {
        pressKey(robot, 100, buttonAssignments[ButtonConstants.START]);

        //simulate load time
        Thread.sleep(2000);
        pickMushroomKingdom(robot);

        //simulate stage load and ready
        Thread.sleep(5000);

        pressKey(robot, 0, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        simulateLoadTime(50);
        pressKey(robot, 2000, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);

        simulateLoadTime(8500);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.START]);
        Thread.sleep(500);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.START]);
    }

    private void simulateLoadTime(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private void pickMushroomKingdom(Robot robot) throws InterruptedException {
        pressKey(robot, 250, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 180, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 0, buttonAssignments[ButtonConstants.A]);
    }

    private void pressKey(Robot robot, int duration, int keyCode) throws InterruptedException {
        robot.keyPress(keyCode);
        Thread.sleep(duration);
        robot.keyRelease(keyCode);
    }
}