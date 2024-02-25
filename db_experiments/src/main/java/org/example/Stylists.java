package org.example;

public class Stylists {
    private int id;
    private String S_name;

    public Stylists(int id, String name) {
        this.id = id;
        this.S_name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return S_name;
    }

    public void setName(String name) {
        this.S_name = name;
    }
}
