package ru.vk.education.job;

import java.util.Set;

public class User {
    private final String name;
    private final Set<String> skills;
    private final int exp;

    public User(String name, Set<String> skills, int exp) {
        this.name = name;
        this.skills = skills;
        this.exp = exp;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (String elem : skills) {
            sb.append(elem);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        String skillsString = sb.toString();

        System.out.println(name + " " + skillsString + " " + exp);
    }

    public String getName() {
        return name;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public int getExp() {
        return exp;
    }
}