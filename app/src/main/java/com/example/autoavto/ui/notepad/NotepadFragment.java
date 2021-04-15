package com.example.autoavto.ui.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.CreateNotePadActivity;
import com.example.autoavto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotepadFragment extends Fragment {
    FloatingActionButton fab;
    TextView text_gallery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        fab = root.findViewById(R.id.fabCreateNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CreateNotePadActivity.class);
                startActivity(i);
            }
        });

        return root;
    }
}