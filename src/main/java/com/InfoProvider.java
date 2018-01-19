package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//wzorzec strategia / Component ????

public class InfoProvider {
    public void getInfo(String fileName, IReader reader) {
        File input = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line = br.readLine();
            while (line != null) {
                reader.read(line, br);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Coś poszło nie tak z plikiem " + fileName);
        }
    }
}
