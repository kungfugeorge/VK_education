package ru.vk.education.job;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        try (BufferedReader br = new BufferedReader(new FileReader(this.file));
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("user ")) {
                    String[] inputArray = line.split(" ");
                    Set<String> skillsSet = new HashSet<>();
                    int exp = 0;
                    String name = inputArray[1];
                    for (String elem : inputArray) {
                        if (elem.startsWith("--skills=")) {
                            String skills = elem.substring(9);
                            String[] skillSArray = skills.split(",");
                            skillsSet.addAll(Arrays.asList(skillSArray));
                        }
                        if (elem.startsWith("--exp=")) {
                            String expNumber = elem.substring(6);
                            exp = Integer.parseInt(expNumber);
                        }
                    }
                    User user = new User(name, skillsSet, exp);
                    ms.addUser(user);

                }
                if (line.startsWith("job ")) {
                    line = line.trim();
                    String[] inputArray = line.split(" ");
                    String vacancyName = inputArray[1];
                    String companyName = null;
                    Set<String> tagsSet = new HashSet<>();
                    int expNeeded = 0;

                    for (String elem : inputArray) {
                        if (elem.startsWith("--company=")) {
                            companyName = elem.substring(10);
                        }
                        if (elem.startsWith("--tags=")) {
                            String tags = elem.substring(7);
                            String[] tagsArray = tags.split(",");
                            tagsSet.addAll(Arrays.asList(tagsArray));
                        }
                        if (elem.startsWith("--exp=")) {
                            String expNumber = elem.substring(6);
                            expNeeded = Integer.parseInt(expNumber);
                        }
                    }

                    Vacancy vacancy = new Vacancy(vacancyName, companyName, tagsSet, expNeeded);
                    ms.addVacancy(vacancy);
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
        try (BufferedReader br = new BufferedReader(new FileReader(this.file));
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
//            System.out.println("Ошибка чтения из файла");
        }
    }
}
