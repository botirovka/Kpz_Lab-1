package botirovka;

import java.util.Set;

public class Athlete {
    private String name;
    private String surname;
    private String sport;
    private int medals;
    private int age;

    public Athlete(String name, String surname, String sport, int medals, int age) {
        this.name = name;
        this.surname = surname;
        this.sport = sport;
        this.medals = medals;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + age + " років) - " + sport + ", медалей: " + medals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getAge() {
        return age;
    }
    public String getId() {
        return name + " " + surname;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
