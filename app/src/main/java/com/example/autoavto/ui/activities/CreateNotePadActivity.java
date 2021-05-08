package com.example.autoavto.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autoavto.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateNotePadActivity extends AppCompatActivity {
    String fileName;
    Button buttonCreate;
    EditText noteName;
    EditText noteText;
    List<File> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_pad);

        noteName = findViewById(R.id.noteName);
        buttonCreate = findViewById(R.id.buttonCreate);
        noteText = findViewById(R.id.noteText);

        buttonCreate.setOnClickListener(v -> {
            try  {
                if(noteName.getText().toString().equals("")){
                    Toast.makeText(CreateNotePadActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                }
                else {
                    fileName = "data/data/com.example.autoavto/files/"+noteName.getText().toString()+".txt";
                    File file = new File(fileName);
                    //create the file.
                    if (file.createNewFile()){
                        Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show();FileWriter writer = new FileWriter (fileName);
                        writer.write(noteText.getText().toString());
                        writer.close();
                        finish();
                    }
                    else{
                        Toast.makeText(this, "Заметка с таким именем уже существует!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
