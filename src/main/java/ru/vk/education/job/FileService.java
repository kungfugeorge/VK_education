package ru.vk.education.job;

import java.io.*;

public class FileService {

    File file;

    public FileService() {
        this.file = new File("commands.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
//                System.out.println("Ошибка создания файла");
            }
        } else {
            init();
        }
    }

    public MatchingService init() {
        MatchingService ms = new MatchingService();
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("user ")) {
                    String[] inputArray = line.split(" ");
                    ms.addUser(inputArray);
                }
                if (line.startsWith("job ")) {
                    line = line.trim();
                    String[] inputArray = line.split(" ");
                    ms.addVacancy(inputArray);
                }
            }
        } catch (IOException ex) {
//            System.out.println("Ошибка чтения из файла");
        }
        return ms;
    }

    public void saveCommand(String str){
        try (FileWriter fileWriter = new FileWriter(this.file, true)) {
            fileWriter.write(str);
            fileWriter.append("\n");
        } catch (IOException io) {
//            System.out.println("Ошибка создания filewriter");
        }
    }

    public void printFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
//            System.out.println("Ошибка чтения из файла");
        }
    }
}