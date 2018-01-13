package com;

public abstract class Person implements Savable {
    protected String firstName;
    protected String lastName;
    protected Long pesel;

    public Person(String firstName, String lastName, Long pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public abstract void info();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return pesel != null ? pesel.equals(person.pesel) : person.pesel == null;

    }

    @Override
    public int hashCode() {
        return pesel != null ? pesel.hashCode() : 0;
    }
}
