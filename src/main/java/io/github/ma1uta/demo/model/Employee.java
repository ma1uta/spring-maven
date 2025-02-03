package io.github.ma1uta.demo.model;

import java.util.List;

public class Employee {

    private Long id;
    private String name;
    private String surname;
    private List<Address> address;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, List<Address> address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
