package com.example.autoavto.ui.activities;



import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.autoavto.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class CreateNotePadActivity extends AppCompatActivity {
    String fileName;
    Button buttonCreate;
    EditText noteName;
    EditText noteText;
    ImageButton buttonBack;
    int b = 1;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_pad);

        noteName = findViewById(R.id.noteName);
        buttonCreate = findViewById(R.id.buttonCreate);
        noteText = findViewById(R.id.noteText);
        buttonBack = findViewById(R.id.buttonBack_promCheck);



        if (getIntent().getSerializableExtra("name") != null) {
            noteName.setText(getIntent().getSerializableExtra("name").toString());
            File file = new File(getFilesDir() + "/" + getIntent().getSerializableExtra("name").toString() + ".txt");
            try {
                FileReader reader = new FileReader(file);
                Scanner scanner = new Scanner(reader);
                while(scanner.hasNextLine()){
                    noteText.setText(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        buttonCreate.setOnClickListener(v -> {

            try  {
                if(noteName.getText().toString().equals("")){
                    Toast.makeText(CreateNotePadActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                }
                else {
                    fileName = getFilesDir() + "/" + noteName.getText().toString() + ".txt";
                    File file = new File(fileName);
                    //create the file.
                    if (file.createNewFile()){
                        Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show();
                        FileWriter writer = new FileWriter (fileName);
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


        // СОЗДАНА КНОПОЧКА ДЛЯ ПЕРЕХОДА НАЗАД БЕЗ СОЗДАНИЯ ЗАМЕТКИ ИЗ АКТИВНОСТИ СОЗДАНИЯ ЗАМЕТОК
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
