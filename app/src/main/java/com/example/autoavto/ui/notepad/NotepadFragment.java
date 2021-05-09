package com.example.autoavto.ui.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ArrayList<String> names;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notes, container, false);
        fab = root.findViewById(R.id.fabCreateNote);
        NotesList = root.findViewById(R.id.NotesList);

        fab.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), CreateNotePadActivity.class);
            startActivity(i);
        });
        update();


        //ТЕПЕРЬ ЗДЕСЬ ЗАДАЕМ ДЕЙСТВИЯ, КОТОРЫЕ БУДУТ ВЫПОЛНЕНЫ ПОСЛЕ НАЖАТИЯ НА ЭЛЕМЕНТ ЛИСТВЬЮ
        NotesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), (position + 1) + " элемент", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public void update() {
        names = search_notes();
        String[] def = new String[]{"Здесь еще нет заметок!"};
        a = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, names);
        ArrayAdapter<String> b = new ArrayAdapter<>(root.getContext(),android.R.layout.simple_list_item_1,def);
        if (names.isEmpty()) {
            NotesList.setAdapter(b);
        } else {
            NotesList.setAdapter(a);
        }
    }
    public ArrayList<String> search_notes(){
        ArrayList<String> names = new ArrayList<>();
        File path = new File("data/data/com.example.autoavto/files");
        String[] files = path.list();
        for(int i = 0;i < files.length; i++){
            if(files[i].contains(".txt")){
                names.add(0, files[i].replace(".txt","")); // ИЗМЕНЕНО: ТЕПЕРЬ ЭЛЕМЕНТ ДОБАВЛЯЕТСЯ В САМОЕ НАЧАЛО СПИСКА
            }
        }
        return names;
    }



}