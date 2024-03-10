package org.example;

public class Stylists {
    private int id;
    private String S_name;
    private boolean S_isDeleted;

    public Stylists(int id, String name, boolean isDeleted) {
        this.id = id;
        this.S_name = name;
        this.S_isDeleted = isDeleted;
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

    public boolean getIsDeleted() {
        return S_isDeleted;
    }

    public void setisDeleted(boolean s_isDeleted) {
        S_isDeleted = s_isDeleted;
    }
}
