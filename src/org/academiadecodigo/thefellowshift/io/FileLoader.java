package org.academiadecodigo.thefellowshift.io;

import org.academiadecodigo.thefellowshift.constant.Constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public void instantiateFileReader() {
        try {
            fileReader = new FileReader(Constant.FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> load() {
        instantiateFileReader();

        List<String> lines = new ArrayList<>();

        String line = "";

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return lines;
    }
}
