package com.example.autoavto.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.autoavto.R;


public class CarToActivity extends AppCompatActivity {
ListView tolist;
TextView carname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_to);
        carname = findViewById(R.id.textCarName);
        tolist = findViewById(R.id.list_TO);
        String[] to = new String[]{"TO - 1 (15 000 км / 12 мес)","TO - 2 (30 000 км / 25 мес)","TO - 3 (45 000 км / 36 мес)",
                "TO - 4 (60 000 км / 48 мес)","TO - 5 (75 000 км / 60 мес)","TO - 6 (90 000 км / 72 мес)",
                "TO - 7 (105 000 км / 84 мес)","TO - 8 (120 000 км / 96 мес)"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,to);
        tolist.setAdapter(adapter);
        String nameCar = getIntent().getStringExtra("namecar");
        carname.setText(nameCar);
        tolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CarToActivity.this,ToActivity.class);
                i.putExtra("to",String.valueOf(position));
                startActivity(i);
            }
        });
    }
}
