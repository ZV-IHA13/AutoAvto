package com.example.autoavto.ui.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.ui.activities.CreateNotePadActivity;
import com.example.autoavto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NotepadFragment extends Fragment {
    FloatingActionButton fab;
    ListView NotesList;
    View root;
    ArrayAdapter<String> a;
    ArrayList<String> names = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notes, container, false);
        fab = root.findViewById(R.id.fabCreateNote);
        NotesList = root.findViewById(R.id.NotesList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CreateNotePadActivity.class);
                startActivity(i);
            }
        });
        update();
        return root;
    }
    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public void update() {
        a = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, names);
        if (names == null) {

        } else {
            NotesList.setAdapter(a);
        }
    }
}