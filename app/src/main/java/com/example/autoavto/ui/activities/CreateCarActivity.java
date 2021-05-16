package com.example.autoavto.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.autoavto.R;

public class CreateCarActivity extends AppCompatActivity {
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7,radioButton8;
    Button button_accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);
        load();

        Spinner dropdown = findViewById(R.id.spinner_car_choose);
        String[] item = new String[]{"KIA sportage 2009"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, item);
        dropdown.setAdapter(adapter);

        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!radioButton1.isChecked()&&!radioButton2.isChecked()
                        &&!radioButton3.isChecked()&&!radioButton4.isChecked()
                        &&!radioButton5.isChecked()&&!radioButton6.isChecked()
                        &&!radioButton7.isChecked()&&!radioButton8.isChecked()){
                    Toast.makeText(CreateCarActivity.this, "Заполнены не все пункты!", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* Здесь будет загрузка с базы сервера */
                finish();
            }
        });

    }
    public void load(){
        button_accept = findViewById(R.id.button_accept);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton6 = findViewById(R.id.radioButton6);
        radioButton7 = findViewById(R.id.radioButton7);
        radioButton8 = findViewById(R.id.radioButton8);

    }
}