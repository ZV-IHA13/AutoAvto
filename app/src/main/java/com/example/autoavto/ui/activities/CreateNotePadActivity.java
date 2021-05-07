package com.example.autoavto.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autoavto.Name;
import com.example.autoavto.R;
import com.example.autoavto.ui.notepad.NotepadFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateNotePadActivity extends AppCompatActivity {
    String fileName;
    Button buttonCreate;
    EditText noteName;
    EditText noteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_pad);
        noteName = findViewById(R.id.noteName);
        buttonCreate = findViewById(R.id.buttonCreate);
        noteText = findViewById(R.id.noteText);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try  {
                    if(noteName.getText().toString().equals("")){
                        Toast.makeText(CreateNotePadActivity.this, "Имя не может содержать пробелов или быть пустым!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Name a = new Name();
                        a.add(noteName.getText().toString());
                        fileName = noteName.getText().toString() + ".txt";
                        FileOutputStream output = openFileOutput(fileName, MODE_PRIVATE);
                        output.write(noteText.getText().toString().getBytes());

                        output.close();
                        Toast.makeText(CreateNotePadActivity.this, "Сохранено!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        /*b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fileName = noteName.getText().toString()+".txt";
                    InputStream inputStream = openFileInput(fileName);

                    if (inputStream != null) {
                        InputStreamReader isr = new InputStreamReader(inputStream);
                        BufferedReader reader = new BufferedReader(isr);
                        String line;
                        StringBuilder builder = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            builder.append(line + "\n");
                        }

                        inputStream.close();
                        Toast.makeText(CreateNotePadActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
         */
    }
}