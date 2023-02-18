package org.academiadecodigo.thefellowshift.io;

import org.academiadecodigo.thefellowshift.constant.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileSaver {

    private PrintWriter printWriter;

    public FileSaver() {
    }

    public void instantiatePrintWriter()  {
        try {
            printWriter = new PrintWriter(Constant.FILE_NAME);
        } catch (FileNotFoundException e) {
            new File(Constant.FILE_NAME);
            try {
                printWriter = new PrintWriter(Constant.FILE_NAME);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void write(String data) {
        instantiatePrintWriter();
        if(data != null && data.trim().length() != 0) {
            printWriter.println(data.trim());
            printWriter.flush();
        }
        printWriter.close();
    }
}
