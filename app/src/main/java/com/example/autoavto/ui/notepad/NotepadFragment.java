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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        NotesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note a = notes.get(position);
                String fileName = a.getFirstText();
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

    public void search_notes() {
        notes.clear();
        File path = new File("data/data/com.example.autoavto/files");
        String[] files = path.list();

        for (int i = 0; i < files.length; i++) {
            File file = new File(path + "/" + files[i]);
            Date date = new Date(file.lastModified());
            Note note = new Note(files[i].replace(".txt", ""), date);
            if (notes.contains(note)) {
                return;
            } else {
                notes.add(note);
            }
        }
        Comparator<Note> sort = new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                if (o1.getDate().after(o2.getDate())) {
                    return -1;
                } else if (o1.getDate().before(o2.getDate())) {
                    return 1;
                }
                return 0;
            }
        };
        Collections.sort(notes, sort);
    }

    public class NoteAdapter extends ArrayAdapter<Note> {
        public NoteAdapter(Context context) {
            super(context, R.layout.my_simple_list_notes, notes);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Note note = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.my_simple_list_notes, null);
            }
            ((TextView) convertView.findViewById(R.id.name))
                    .setText(note.getFirstText());
            String month = "";
            switch (note.getDate().getMonth()) {
                case (0):
                    month = "Янв";
                case (1):
                    month = "Фев";
                case (2):
                    month = "Мар";
                case (3):
                    month = "Апр";
                case (4):
                    month = "Май";
                case (5):
                    month = "Июн";
                case (6):
                    month = "Июл";
                case (7):
                    month = "Авг";
                case (8):
                    month = "Сен";
                case (9):
                    month = "Окт";
                case (10):
                    month = "Ноя";
                case (11):
                    month = "Дек";
            }
            ((TextView) convertView.findViewById(R.id.capital))
                    .setText(month + " " + note.getDate().getDate() + " " + note.getDate().getHours() + ":" + note.getDate().getMinutes());
            return convertView;
        }
    }
}
