package ru.vk.education.job;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MatchingService matchingService = new MatchingService();
        FileService fs = new FileService();
        matchingService = fs.init();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine().trim();
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case ("user") :
                    matchingService = addUser(inputArray, matchingService);
                    Optional<User> user = matchingService.getLastUser();
                    if (user.isPresent()) {
                        fs.saveCommand(input);
                    }
                    break;
                case ("job") :
                    matchingService = addVacancy(inputArray, matchingService);
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
                case ("exit") :
                    isExit = true;
                    break;
            }
        }
    }


    private static MatchingService addVacancy(String[] inputArray, MatchingService matchingService) {
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
        matchingService.addVacancy(vacancy);

        return matchingService;
    }

    private static MatchingService addUser(String[] inputArray, MatchingService matchingService) {
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
        matchingService.addUser(user);

        return matchingService;
    }
}
