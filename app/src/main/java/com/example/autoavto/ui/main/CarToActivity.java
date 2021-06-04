package com.example.autoavto.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autoavto.R;

import java.io.File;


public class CarToActivity extends AppCompatActivity {
ListView tolist;
TextView carname;
Button delete;
    String nameCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_to);
        carname = findViewById(R.id.textCarName);
        tolist = findViewById(R.id.list_TO);
        delete = findViewById(R.id.buttonDeleteCar);
        String[] to = new String[]{"TO - 1 (15 000 км / 12 мес)","TO - 2 (30 000 км / 25 мес)","TO - 3 (45 000 км / 36 мес)",
                "TO - 4 (60 000 км / 48 мес)","TO - 5 (75 000 км / 60 мес)","TO - 6 (90 000 км / 72 мес)",
                "TO - 7 (105 000 км / 84 мес)","TO - 8 (120 000 км / 96 мес)"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,to);
        tolist.setAdapter(adapter);
        nameCar = getIntent().getStringExtra("namecar");
        carname.setText(nameCar);
        tolist.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(CarToActivity.this,ToActivity.class);
            i.putExtra("carname",nameCar);
            i.putExtra("to",String.valueOf(position));
            startActivity(i);
        });
        delete.setOnClickListener(v -> {
            File file = new File(getCacheDir() + "/"+nameCar+".txt");
            if(file.delete()){
                Toast.makeText(CarToActivity.this, "Удалено!", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
