package com.example.autoavto;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Name {
    public static ArrayList<String> names = new ArrayList<>();

    public Name() {
    }
    public void add(String name){
        if(name!=null) {
            names.add(name);
            System.out.println(names);
        }
    }
    public ArrayList getArray(){
        return names;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();    }
}