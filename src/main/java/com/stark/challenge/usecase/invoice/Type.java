package com.stark.challenge.usecase.invoice;

public enum Type {
    PAID("paid"),
    CREATED("created");

    private String name;


    public static Type fromString(String name) {
        for (Type type : Type.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }

    Type(String name) {
        this.name = name;
    }
}
