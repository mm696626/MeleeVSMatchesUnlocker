package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MeleeUnlockProgressUI extends JFrame implements KeyListener {


    private int vsMatches;
    private JLabel unlockProgress;


    public MeleeUnlockProgressUI(int vsMatches) throws InterruptedException, AWTException {
        this.vsMatches = vsMatches;

        setTitle("Unlocking in Progress VS Matches Unlocker");
        generateUI();
        unlock();
    }

    private void generateUI() {
        unlockProgress = new JLabel(vsMatches + " VS Matches on Record!");

        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(1, 1);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        mainMenuPanel.add(unlockProgress);
        add(mainMenuPanel);
    }

    private void unlock() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        JOptionPane.showMessageDialog(this, "Boot Melee and click into your window within 2 seconds of closing this box");
        Thread.sleep(2000);

        //START
        for (int i=0; i<2; i++) {
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

        //simulate load time
        Thread.sleep(3000);

        //press down to go to Melee mode
        robot.keyPress(KeyEvent.VK_G);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_G);

        Thread.sleep(100);

        //press A twice
        for (int i=0; i<2; i++) {
            robot.keyPress(KeyEvent.VK_X);
            Thread.sleep(100);
            robot.keyRelease(KeyEvent.VK_X);
        }

        //simulate loading time
        Thread.sleep(5000);

        //Pick Fox
        robot.keyPress(KeyEvent.VK_H);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_H);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(450);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_X);

        //up
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_T);

        //right
        robot.keyPress(KeyEvent.VK_H);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_H);

        //go to rules
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_X);

        Thread.sleep(500);

        //press right to change to stock
        robot.keyPress(KeyEvent.VK_H);
        robot.keyRelease(KeyEvent.VK_H);

        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);

        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);

        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);

        //back out to CSS
        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_Z);


        //Turn on a CPU
        robot.keyPress(KeyEvent.VK_H);
        Thread.sleep(180);
        robot.keyRelease(KeyEvent.VK_H);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(250);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_X);

        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //simulate load time
        Thread.sleep(2000);

        //pick Mushroom Kingdom
        robot.keyPress(KeyEvent.VK_H);
        Thread.sleep(250);
        robot.keyRelease(KeyEvent.VK_H);

        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(180);
        robot.keyRelease(KeyEvent.VK_T);

        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_X);

        //simulate stage load and ready
        Thread.sleep(5000);

        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);
        Thread.sleep(50);
        robot.keyPress(KeyEvent.VK_F);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_F);

    }

    private void pressLeft(int delay) {

    }

    private void pressRight(int delay) {

    }

    private void pressUp(int delay) {

    }

    private void pressDown(int delay) {

    }

    private void pressStart(int delay) {

    }

    private void pressA(int delay) {

    }

    private void pressB(int delay) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}