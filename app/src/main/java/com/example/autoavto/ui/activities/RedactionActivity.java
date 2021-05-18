package com.example.autoavto.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.autoavto.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RedactionActivity extends AppCompatActivity {
    Button buttonCreate;
    EditText noteName;
    EditText noteText;
    ImageButton buttonBack;
    String fileName;
    Button buttonNoteDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redaction);


        noteName = findViewById(R.id.noteNameRedact);
        buttonCreate = findViewById(R.id.buttonCreateFrRedact);
        noteText = findViewById(R.id.noteTextRedact);
        buttonBack = findViewById(R.id.buttonBack_promRedact);
        buttonNoteDelete = findViewById(R.id.buttonNoteDelete);

        buttonNoteDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getFilesDir() + "/" + getIntent().getSerializableExtra("name").toString() + ".txt");
                if (file.delete()) {
                    Toast.makeText(RedactionActivity.this, "Заметка удалена", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (getIntent().getSerializableExtra("name") != null) {
            String namefile = getIntent().getSerializableExtra("name").toString();
            noteName.setText(namefile);
            File file = new File(getFilesDir() + "/" + getIntent().getSerializableExtra("name").toString() + ".txt");
            try {
                FileReader reader = new FileReader(file);
                Scanner scanner = new Scanner(reader);
                String text = "";
                while (scanner.hasNextLine()) {
                    text = text + scanner.nextLine() + "\n";
                }
                noteText.setText(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            buttonCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noteName.getText().toString().equals("")) {
                        Toast.makeText(RedactionActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                    } else {
                        File file = new File(getFilesDir() + "/" + namefile + ".txt");
                        if (file.exists()) {
                            writeinfile(file.getPath());
                            Toast.makeText(RedactionActivity.this, "Сохранено!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RedactionActivity.this, "Заметка с таким именем уже существует!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }

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