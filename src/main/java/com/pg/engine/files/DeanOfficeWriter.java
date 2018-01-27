package com.pg.engine.files;

import com.pg.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

public class DeanOfficeWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeanOfficeWriter.class);

    /**
     * Metoda zapisująca dany obiekt do pliku
     *
     * @param s obiekt do zapisania (musi implementować com.pg.engine.files.ISaver)
     */
    public void save(ISaver s) {
        File output = new File(s.getFileName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output, true))) {
            String dataToSave = s.getDataToSave();
            bw.write(dataToSave);
            bw.newLine();
        } catch (IOException e) {
            LOGGER.error("Problem with saving to: " + s.getFileName(), e);
        }
    }

    public void save(Map<Integer, ? extends Person> people) {
        String fileName = people.get(1).getFileName();
        File output = new File(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output, true))) {
            for (Map.Entry<Integer, ? extends Person> entry : people.entrySet()) {
                String dataToSave = entry.getValue().getDataToSave();
                bw.write(dataToSave);
                bw.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Problem with saving to: " + fileName, e);
        }
    }
}
