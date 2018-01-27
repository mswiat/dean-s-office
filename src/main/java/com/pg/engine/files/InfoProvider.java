package com.pg.engine.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class InfoProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoProvider.class);
    public void readData(String fileName, IReader reader) {
        File input = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line = br.readLine();
            while (line != null) {
                reader.read(line, br);
                line = br.readLine();
            }
        } catch (IOException e) {
            LOGGER.error("Problem with reading data from: " + fileName, e);
        }
    }
}
