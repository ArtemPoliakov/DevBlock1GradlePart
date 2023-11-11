package org.example;

import com.google.gson.GsonBuilder;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                        .toJson(Me.getInstance())
        );
    }
}