package org.example;

public class Clients {
    private int id;
    private String name;
    private boolean isDeleted;
    private Stylists idStylists;

    public Clients(int id, String name, boolean isDeleted, Stylists idStylists) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.idStylists = idStylists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stylists getIdStylists() {
        return idStylists;
    }

    public void setIdStylists(Stylists idStylists) {
        this.idStylists = idStylists;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
