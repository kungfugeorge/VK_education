package ru.vk.education.job;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileService fs = new FileService();
        MatchingService matchingService = fs.init();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine().trim();
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case ("user") :
                    matchingService.addUser(inputArray);
                    Optional<User> user = matchingService.getLastUser();
                    if (user.isPresent()) {
                        fs.saveCommand(input);
                    }
                    break;
                case ("job") :
                    matchingService.addVacancy(inputArray);
                    Optional<Vacancy> vacancy = matchingService.getLastVacancy();
                    if (vacancy.isPresent()) {
                        fs.saveCommand(input);
                    }
                    break;
                case ("user-list") :
                    matchingService.showUsers();
                    fs.saveCommand("user-list");
                    break;
                case ("job-list") :
                    matchingService.showVacancies();
                    fs.saveCommand("job-list");
                    break;
                case ("suggest") :
                    matchingService.findMatches(inputArray[1]);
                    fs.saveCommand("suggest " + inputArray[1]);
                    break;
                case ("history") :
                    fs.printFile();
                    fs.saveCommand("history");
                    break;
                case ("stat") :
                    matchingService.printStat(inputArray);
                    fs.saveCommand(input);
                    break;
                case ("exit") :
                    isExit = true;
                    break;
            }
        }
    }
}
