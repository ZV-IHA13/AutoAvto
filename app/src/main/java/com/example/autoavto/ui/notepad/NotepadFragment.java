package com.example.autoavto.ui.notepad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.R;

public class NotepadFragment extends Fragment {

    TextView text_gallery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        text_gallery = root.findViewById(R.id.text_gallery);
        text_gallery.setText("Это потенциальный гараж");

        return root;
    }
}