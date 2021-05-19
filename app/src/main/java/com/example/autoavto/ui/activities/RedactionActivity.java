package com.example.autoavto.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autoavto.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RedactionActivity extends AppCompatActivity {
    Button buttonRedo;
    EditText noteName;
    EditText noteText;
    Button buttonBack;
    Button buttonNoteDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redaction);

        noteName = findViewById(R.id.noteNameRedact);
        buttonRedo = findViewById(R.id.buttonCreateFrRedact);
        noteText = findViewById(R.id.noteTextRedact);
        buttonBack = findViewById(R.id.buttonBack_fromRedo);
        buttonNoteDelete = findViewById(R.id.buttonDeleteFile);

        buttonNoteDelete.setOnClickListener(v -> {
            File file = new File(getFilesDir() + "/" + getIntent().getSerializableExtra("name").toString() + ".txt");
            if (file.delete()) {
                Toast.makeText(RedactionActivity.this, "Заметка удалена", Toast.LENGTH_SHORT).show();
                finish();
            }
        });//удаление файла

        buttonBack.setOnClickListener(v -> finish());//возвращение в активность
            //открытие файла и вывод его содержимого
            String namefile = getIntent().getSerializableExtra("name").toString();
            noteName.setText(namefile);
            File file = new File(getFilesDir() + "/" + getIntent().getSerializableExtra("name").toString() + ".txt");
            try {
                FileReader reader = new FileReader(file);
                Scanner scanner = new Scanner(reader);
                StringBuilder text = new StringBuilder();
                while (scanner.hasNextLine()) {
                    text.append(scanner.nextLine()).append("\n");
                }
                noteText.setText(text.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //
            buttonRedo.setOnClickListener(v -> {
                if (noteName.getText().toString().equals("")) {
                    Toast.makeText(RedactionActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                } else {
                    File file1 = new File(getFilesDir() + "/" + namefile + ".txt");
                    if (file1.exists()) {
                        writeinfile(file1.getPath());
                        Toast.makeText(RedactionActivity.this, "Сохранено!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RedactionActivity.this, "Заметка с таким именем уже существует!", Toast.LENGTH_SHORT).show();
                    }
                }
            });//редактирование файла

        }

    public void writeinfile (String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(noteText.getText().toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}