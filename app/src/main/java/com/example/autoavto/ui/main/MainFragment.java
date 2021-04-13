package com.example.autoavto.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.SecureKeyImportUnavailableException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.R;
import com.example.autoavto.ui.CreateCarActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainFragment extends Fragment {
FloatingActionButton fab ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        fab = root.findViewById(R.id.fabcreate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), CreateCarActivity.class);
                startActivity(i);
            }
        });
        return root;
    }
}