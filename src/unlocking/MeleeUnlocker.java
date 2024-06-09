package unlocking;

import constants.ButtonConstants;
import io.VSMatchStatSaver;

import java.awt.*;

public class MeleeUnlocker {


    private int vsMatches;
    private int vsMatchesTarget;
    private int foxVsMatches;
    private int[] buttonAssignments;


    public MeleeUnlocker(int vsMatches, int vsMatchesTarget, int foxVsMatches, int[] buttonAssignments) throws InterruptedException, AWTException {
        this.vsMatches = vsMatches;
        this.vsMatchesTarget = vsMatchesTarget;
        this.foxVsMatches = foxVsMatches;
        this.buttonAssignments = buttonAssignments;
        unlock(buttonAssignments);
    }

    private void unlock(int[] buttonAssignments) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        simulateWaitOrLoadTime(2000);

        //press down to go to Melee mode
        pressKey(robot, 200, buttonAssignments[ButtonConstants.DOWN_ON_STICK]);
        simulateWaitOrLoadTime(200);

        //press A twice
        for (int i=0; i<2; i++) {
            pressKey(robot, 200, buttonAssignments[ButtonConstants.A]);
            simulateWaitOrLoadTime(100);
        }

        //simulate loading time
        simulateWaitOrLoadTime(5000);

        //Pick Fox
        pressKey(robot, 100, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 450, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);

        //go to rules
        pressKey(robot, 200, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);

        simulateWaitOrLoadTime(500);

        //press right to change to stock
        pressKey(robot, 100, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);

        simulateWaitOrLoadTime(1000);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.DOWN_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        simulateWaitOrLoadTime(100);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.B]);


        //Turn on a CPU
        pressKey(robot, 180, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 250, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);
        simulateWaitOrLoadTime(200);

        //start loop of VS matches
        for (int i=vsMatches; i<vsMatchesTarget; i++) {
            doVSMatch(robot);
            simulateWaitOrLoadTime(4000);

            //press start to go to stage select after match
            pressKey(robot, 200, buttonAssignments[ButtonConstants.START]);
        }

        VSMatchStatSaver vsMatchStatSaver = new VSMatchStatSaver();
        vsMatchStatSaver.saveVSMatchStatsToFile(vsMatches, foxVsMatches);

        //pause after done
        pressKey(robot, 200, buttonAssignments[ButtonConstants.PAUSE_HOTKEY]);
        simulateWaitOrLoadTime(1000);
        System.exit(0); //close program after finished!
    }

    private void doVSMatch(Robot robot) throws InterruptedException {
        pressKey(robot, 200, buttonAssignments[ButtonConstants.START]);

        //simulate load time
        simulateWaitOrLoadTime(2000);
        pickMushroomKingdom(robot);

        //simulate stage load and ready
        simulateWaitOrLoadTime(5000);

        //dash to the left to SD
        pressKey(robot, 50, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);
        simulateWaitOrLoadTime(50);
        pressKey(robot, 2000, buttonAssignments[ButtonConstants.LEFT_ON_STICK]);

        //wait for results screen to end and mash Start
        simulateWaitOrLoadTime(8500); //this could be shorter, but I wanted to guarantee the victory animation would finish
        pressKey(robot, 200, buttonAssignments[ButtonConstants.START]);
        simulateWaitOrLoadTime(500);
        pressKey(robot, 200, buttonAssignments[ButtonConstants.START]);

        vsMatches++;
        foxVsMatches++;

        //edge case where if the number of Fox's VS matches is a multiple of 100 and is less than 300, you get a trophy, so this presses A to get past the prompt
        if (foxVsMatches % 100 == 0 && foxVsMatches <= 300) {
            simulateWaitOrLoadTime(1000);
            pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);
        }
    }

    private void simulateWaitOrLoadTime(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private void pickMushroomKingdom(Robot robot) throws InterruptedException {
        pressKey(robot, 250, buttonAssignments[ButtonConstants.RIGHT_ON_STICK]);
        pressKey(robot, 180, buttonAssignments[ButtonConstants.UP_ON_STICK]);
        pressKey(robot, 100, buttonAssignments[ButtonConstants.A]);
    }

    private void pressKey(Robot robot, int duration, int keyCode) throws InterruptedException {
        robot.keyPress(keyCode);
        Thread.sleep(duration);
        robot.keyRelease(keyCode);
    }
}