package com.example.lab3;

import java.util.ArrayList;

public class User {
    public final long id;
    private final String name;
    private final String email;

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void Display() {
        System.out.println(id + "\n" + name + "\n" + email);
    }


}
