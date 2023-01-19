package com.example.eindopdrachtbackendv1.models;


public enum Rating {
    ZEROSTARS ("Zero stars"),
    ONESTAR ("One star"),
    TWOSTARS ( "Two stars"),
    THREESTARS ( "Three stars"),
    FOURSTARS ( "Four stars"),
    FIVESTARS ( "Five stars");

    private final String name;

    Rating(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
