package com;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DeanOfficeWriter {
    /**
     * Metoda zapisująca dany obiekt do pliku
     *
     * @param s obiekt do zapisania (musi implementować com.Savable)
     */

    public void save(Savable s) {
        File output = new File(s.getFileName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output, true))) {
            String dataToSave = s.getDataToSave();
            bw.write(dataToSave);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Coś poszło nie tak z plikiem " + s.getFileName());
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
            e.printStackTrace();
            System.out.println("Coś poszło nie tak z plikiem " + fileName);
        }
    }
}
