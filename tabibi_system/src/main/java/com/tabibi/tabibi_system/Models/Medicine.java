package com.tabibi.tabibi_system.Models;



public class Medicine {

    private int id;

    private String name;


    public Medicine() {
    }

    public Medicine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Medicine id(int id) {
        setId(id);
        return this;
    }

    public Medicine name(String name) {
        setName(name);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
