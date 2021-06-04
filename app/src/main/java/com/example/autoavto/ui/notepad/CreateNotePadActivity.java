package com.example.autoavto.ui.notepad;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autoavto.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class CreateNotePadActivity extends AppCompatActivity {
    String fileName;
    Button buttonCreate;
    EditText noteName;
    EditText noteText;
    Button buttonBack;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_pad);

        noteName = findViewById(R.id.noteName);
        buttonCreate = findViewById(R.id.buttonCreate);
        noteText = findViewById(R.id.noteText);
        buttonBack = findViewById(R.id.buttonBack_fromCreate);
            buttonCreate.setOnClickListener(v -> {

                try {
                    if (noteName.getText().toString().equals("")) {
                        Toast.makeText(CreateNotePadActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                    } else {
                        fileName = getFilesDir() + "/" + noteName.getText().toString() + ".txt";
                        File filecreate = new File(fileName);
                        //create the file.
                        if (filecreate.createNewFile()) {
                            Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show();
                            writeinfile(fileName);
                            finish();
                        } else {
                            Toast.makeText(this, "Заметка с таким именем уже существует!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            buttonBack.setOnClickListener(v -> finish());

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

