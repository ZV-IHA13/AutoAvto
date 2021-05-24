package com.example.autoavto.ui.notepad;

import android.content.Context;
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

import com.example.autoavto.Note;
import com.example.autoavto.ui.activities.CreateNotePadActivity;
import com.example.autoavto.R;
import com.example.autoavto.ui.activities.RedactionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotepadFragment extends Fragment {
    FloatingActionButton fab;
    ListView NotesList;
    View root;


    private static final List<Note> notes = new ArrayList<Note>();

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
                Note a = notes.get(position);
                String fileName = a.firstText;
                Intent i = new Intent(getContext(), RedactionActivity.class);

                i.putExtra("name", fileName);
                startActivity(i);
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
        search_notes();
        ArrayAdapter<Note> a = new NoteAdapter(root.getContext());
        NotesList.setAdapter(a);

    }
    public void search_notes(){
        notes.clear();
        File path = new File("data/data/com.example.autoavto/files");
        String[] files = path.list();

        for (int i = 0; i < files.length; i++) {

            File file = new File(path + "/" + files[i]);
            Date date = new Date(file.lastModified());
            Note note = new Note(files[i].replace(".txt", ""), date.toString());
            if (notes.contains(note)) {
                return;
            } else {
                notes.add(0, note);
            }
        }
    }

    public class NoteAdapter extends ArrayAdapter<Note> {



        public NoteAdapter(Context context) {
            super(context, R.layout.my_simple_list_item, notes);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            Note note = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.my_simple_list_item, null);
            }
            ((TextView) convertView.findViewById(R.id.name))
                    .setText(note.firstText);
            ((TextView) convertView.findViewById(R.id.capital))
                    .setText(note.date);
            return convertView;
        }
    }


}
