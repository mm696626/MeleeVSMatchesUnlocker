package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class VSMatchStatSaver {

    public void saveVSMatchStatsToFile(int vsMatches, int foxVsMatches) {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter( new FileOutputStream("vsMatchStats.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("File does not exist");
            System.exit(0);
        }

        outputStream.println("VS Matches" + "=" + vsMatches);
        outputStream.println("Fox VS Matches" + "=" + foxVsMatches);

        outputStream.close();
    }
}
