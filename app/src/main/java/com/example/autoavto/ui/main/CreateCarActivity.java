package com.example.autoavto.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.autoavto.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCarActivity extends AppCompatActivity {
    private static String item;
    Button button_accept;
    Spinner dropdown;
    List<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);
        load();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.0.158:8452").build();
        CarService service = retrofit.create(CarService.class);
        Call<CarNames[]> call = service.getCar();
        call.enqueue(new Callback<CarNames[]>() {
            @Override
            public void onResponse(@NotNull Call<CarNames[]> call, @NotNull Response<CarNames[]> response) {
                setItems(response);
            }
            @Override
            public void onFailure(@NotNull Call<CarNames[]> call, @NotNull Throwable t) {
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
        button_accept.setOnClickListener(view -> {
            if(item==null){
                Toast.makeText(CreateCarActivity.this, "Проверьте ваше подключение к интернету и перезайдите!", Toast.LENGTH_SHORT).show();
                return;}
            File file = new File(getCacheDir()+"/"+item+".txt");
            if(file.exists()){
                Toast.makeText(CreateCarActivity.this, "Данная машина уже добавлена!", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

    }
    public void load() {
        dropdown = findViewById(R.id.spinner_car_choose);
        button_accept = findViewById(R.id.button_accept);
    }
    public void setItems(Response<CarNames[]> response){
        for(int i = 0; i< Objects.requireNonNull(response.body()).length; i++) {
            items.add(response.body()[i].getCarName());
            System.out.println(items.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        dropdown.setAdapter(adapter);
    }

}