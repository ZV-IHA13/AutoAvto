package com.example.autoavto;

import java.util.Objects;

public class Note {

    public String firstText;
    public String date;

    public Note(String firstText, String date) {
        this.firstText = firstText;
        this.date = date;
    }

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return firstText.equals(note.firstText) &&
                date.equals(note.date);
         }




}
