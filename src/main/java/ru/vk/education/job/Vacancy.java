package ru.vk.education.job;

import java.util.Set;

public class Vacancy {
    private final String vacancy;
    private final String companyName;
    private final Set<String> tags;
    private final int expNeeded;

    public Vacancy(String vacancy, String companyName, Set<String> tags, int expNeeded) {
        this.vacancy = vacancy;
        this.companyName = companyName;
        this.tags = tags;
        this.expNeeded = expNeeded;
    }

    public String getString() {
        return vacancy + " at " + companyName;
    }
    public void print() {
        System.out.println(vacancy + " at " + companyName);
    }

    public Set<String> getTags() {
        return tags;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public String getVacancy() {
        return vacancy;
    }
}

