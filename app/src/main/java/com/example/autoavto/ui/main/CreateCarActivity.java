package com.example.autoavto.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.security.keystore.StrongBoxUnavailableException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.autoavto.R;
import com.example.autoavto.ui.To_information;
import com.example.autoavto.ui.CarService;
import com.example.autoavto.ui.settings.CarNames;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCarActivity extends AppCompatActivity {
    private static String item;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8;
    Button button_accept;
    Spinner dropdown;
    List<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);
        load();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2:8452").build();
        CarService service = retrofit.create(CarService.class);
        Call<CarNames[]> call = service.getCar();
        call.enqueue(new Callback<CarNames[]>() {
            @Override
            public void onResponse(Call<CarNames[]> call, Response<CarNames[]> response) {
                setItems(response);
            }

            @Override
            public void onFailure(Call<CarNames[]> call, Throwable t) {
                t.printStackTrace();
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(items.size());
                item = items.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!radioButton1.isChecked() && !radioButton2.isChecked()
                        && !radioButton3.isChecked() && !radioButton4.isChecked()
                        && !radioButton5.isChecked() && !radioButton6.isChecked()
                        && !radioButton7.isChecked() && !radioButton8.isChecked()) {
                    Toast.makeText(CreateCarActivity.this, "Заполнены не все пункты!", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });

    }
    public void load() {
        dropdown = findViewById(R.id.spinner_car_choose);
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
    public void setItems(Response<CarNames[]> response){
        for(int i =0;i<response.body().length;i++) {
            items.add(response.body()[i].getCarName());
            System.out.println(items.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        dropdown.setAdapter(adapter);
    }
    public static String getNameCar(){
        return item;
    }
}