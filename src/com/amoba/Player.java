package com.amoba;

enum Icon{
    X,
    O
}

public class Player {
    public Icon icon;
    public int id;

    public Player(Icon icon, int id) {
        this.icon = icon;
        this.id = id;
    }
}
