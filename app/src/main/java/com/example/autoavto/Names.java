package com.example.autoavto;

import java.util.ArrayList;

public class Names {
    ArrayList<String> names;

    public Names() {
    }
    public void add(String name){
        if(name!=null) {
            names.add(name);
        }
    }
    public ArrayList getArray(){
        return names;
    }
}
