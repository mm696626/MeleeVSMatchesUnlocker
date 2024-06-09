package unlocking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MeleeUnlocker extends JFrame {


    private int vsMatches;
    private int vsMatchesTarget;


    public MeleeUnlocker(int vsMatches, int vsMatchesTarget) throws InterruptedException, AWTException {
        this.vsMatches = vsMatches;
        this.vsMatchesTarget = vsMatchesTarget;
        unlock();
    }

    private void unlock() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        JOptionPane.showMessageDialog(this, "Found unlockable at " + vsMatchesTarget + " VS matches!" + " Boot Melee and click into your window within 2 seconds of closing this box");
        Thread.sleep(2000);

        //no point in unlocking if vs match target is too low
        if (vsMatchesTarget < vsMatches) {
            JOptionPane.showMessageDialog(this, "Invalid VS match count for unlockables you have!");
            setVisible(false);
            return;
        }

        //START
        for (int i=0; i<2; i++) {
            pressKey(robot, 1000, KeyEvent.VK_ENTER);
        }

        //simulate load time
        simulateLoadTime(3000);

        //press down to go to Melee mode
        pressKey(robot, 100, KeyEvent.VK_G);
        simulateLoadTime(100);

        //press A twice
        for (int i=0; i<2; i++) {
            pressKey(robot, 100, KeyEvent.VK_X);
        }

        //simulate loading time
        simulateLoadTime(5000);

        //Pick Fox
        pressKey(robot, 100, KeyEvent.VK_H);
        pressKey(robot, 450, KeyEvent.VK_T);
        pressKey(robot, 0, KeyEvent.VK_X);

        //up and right
        pressKey(robot, 200, KeyEvent.VK_T);
        pressKey(robot, 100, KeyEvent.VK_H);

        //go to rules
        pressKey(robot, 0, KeyEvent.VK_X);

        simulateLoadTime(500);

        //press right to change to stock
        pressKey(robot, 0, KeyEvent.VK_H);

        simulateLoadTime(1000);

        pressKey(robot, 0, KeyEvent.VK_G);


        pressKey(robot, 0, KeyEvent.VK_F);
        pressKey(robot, 0, KeyEvent.VK_F);
        pressKey(robot, 0, KeyEvent.VK_Z);


        //Turn on a CPU
        pressKey(robot, 180, KeyEvent.VK_H);
        pressKey(robot, 250, KeyEvent.VK_T);
        pressKey(robot, 0, KeyEvent.VK_X);

        simulateLoadTime(100);

        for (int i=vsMatches; i<vsMatchesTarget; i++) {
            doVSMatch(robot);
            simulateLoadTime(4000);
            pressKey(robot, 100, KeyEvent.VK_ENTER);
        }

        pressKey(robot, 100, KeyEvent.VK_P);
        JOptionPane.showMessageDialog(this, "Unlock target reached! Pausing game. Press your resume hotkey to get your unlockable!");
        setVisible(false);
    }

    private void doVSMatch(Robot robot) throws InterruptedException {
        pressKey(robot, 100, KeyEvent.VK_ENTER);

        //simulate load time
        Thread.sleep(2000);
        pickMushroomKingdom(robot);

        //simulate stage load and ready
        Thread.sleep(5000);

        pressKey(robot, 0, KeyEvent.VK_F);
        simulateLoadTime(50);
        pressKey(robot, 2000, KeyEvent.VK_F);

        simulateLoadTime(8500);
        pressKey(robot, 100, KeyEvent.VK_ENTER);
        Thread.sleep(500);
        pressKey(robot, 100, KeyEvent.VK_ENTER);
    }

    private void simulateLoadTime(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private void pickMushroomKingdom(Robot robot) throws InterruptedException {
        pressKey(robot, 250, KeyEvent.VK_H);
        pressKey(robot, 180, KeyEvent.VK_T);
        pressKey(robot, 0, KeyEvent.VK_X);
    }

    private void pressKey(Robot robot, int duration, int keyCode) throws InterruptedException {
        robot.keyPress(keyCode);
        Thread.sleep(duration);
        robot.keyRelease(keyCode);
    }
}