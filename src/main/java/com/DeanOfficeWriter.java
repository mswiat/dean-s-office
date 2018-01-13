package com;

import java.io.*;
import java.util.List;

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

    //Powinno być list<Person> ???????????    Jak zrobić bardziej ogólną metodę?
    public void save(List<? extends Person> people){
        String fileName = people.get(0).getFileName();
        File output = new File(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output, true))) {
            for(Person person : people) {
                String dataToSave = person.getDataToSave();
                bw.write(dataToSave);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Coś poszło nie tak z plikiem " + fileName);
        }
    }
}
