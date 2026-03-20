package ru.vk.education.job;

import java.util.*;

public class MatchingService {
    private List<User> users = new ArrayList<>();
    private List<Vacancy> vacancies = new ArrayList<>();

    public Optional<User> getLastUser() {
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(users.size() - 1));
    }

    public Optional<Vacancy> getLastVacancy() {
        return vacancies.isEmpty() ? Optional.empty() : Optional.of(vacancies.get(vacancies.size() - 1));
    }

    public void addUser(User user) {
        boolean isExists = false;
        for (User userElem : users) {
            if (userElem.getName().equals(user.getName())) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            users.add(user);
        }
    }

    public void addVacancy(Vacancy vacancy){
        boolean isExists = false;
        for (Vacancy vacElem : vacancies) {
            if (vacElem.getVacancy().equals(vacancy.getVacancy())) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            vacancies.add(vacancy);
        }
    }

    public void showUsers() {
        for (User elem : users) {
            elem.print();
        }
    }

    public void showVacancies() {
        for (Vacancy elem : vacancies) {
            elem.print();
        }
    }

    public void findMatches(String userName) {
        Map<Vacancy, Integer> vacanciesRating = new HashMap<>();
        boolean userFound = false;

        for (User userElem : users) {
            if (userElem != null && userName.equals(userElem.getName())) {
                userFound = true;
                Set<String> currentUserSkills = userElem.getSkills();
                if (currentUserSkills == null) currentUserSkills = new HashSet<>();

                int userExp = userElem.getExp();

                for (Vacancy vacElem : vacancies) {
                    if (vacElem == null) continue;

                    Set<String> currentVacancyTags = vacElem.getTags();
                    if (currentVacancyTags == null) currentVacancyTags = new HashSet<>();

                    int requiredExp = vacElem.getExpNeeded();
                    int matchCount = 0;

                    for (String skill : currentUserSkills) {
                        if (skill != null && currentVacancyTags.contains(skill)) {
                            matchCount++;
                        }
                    }

                    if (userExp < requiredExp) {
                        matchCount = matchCount / 2;
                    }

                    if (matchCount > 0) {
                        vacanciesRating.put(vacElem, matchCount);
                    }
                }
                break;
            }
        }

        if (!userFound || vacanciesRating.isEmpty()) {
            System.out.println("No matches found");
            return;
        }

        List<Vacancy> result = new ArrayList<>(vacanciesRating.keySet());
        result.sort((v1, v2) -> {
            Integer score1 = vacanciesRating.get(v1);
            Integer score2 = vacanciesRating.get(v2);
            if (score1 == null) score1 = 0;
            if (score2 == null) score2 = 0;
            return Integer.compare(score2, score1);
        });

        if (result.size() > 0) {
            result.get(0).print();
        }
        if (result.size() > 1) {
            result.get(1).print();
        }
    }
}

