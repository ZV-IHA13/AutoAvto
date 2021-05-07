package com.example.autoavto.ui.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.Names;
import com.example.autoavto.ui.activities.CreateNotePadActivity;
import com.example.autoavto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotepadFragment extends Fragment {
    FloatingActionButton fab;
    public ListView NotesList;
    public ArrayList<String> names_notes;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        fab = root.findViewById(R.id.fabCreateNote);
        NotesList = root.findViewById(R.id.NotesList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CreateNotePadActivity.class);
                startActivity(i);
            }
        });
        Names b = new Names();
        names_notes = b.getArray();
        ArrayAdapter<String> a = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1,names_notes);
        NotesList.setAdapter(a);
        return root;
    }
}