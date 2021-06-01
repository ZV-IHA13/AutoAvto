package com.example.autoavto.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.autoavto.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarToActivity extends AppCompatActivity {
ListView tolist;
TextView carname;
    List<To_information> to;
    ArrayAdapter<To_information> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_to);
        carname = findViewById(R.id.textCarName);
        tolist = findViewById(R.id.list_TO);
        String nameCar = getIntent().getStringExtra("namecar");
        carname.setText(nameCar);
        File file = new File(getCacheDir()+"/"+nameCar+".txt");
        String text = "";
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                text = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int current_to = Integer.parseInt(text);
        if(Integer.parseInt(text)!=0){
            to  = searchTo(current_to);
        }
    }

    public List<To_information> searchTo(int to) {
        List<To_information> to_information = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2:8452").build();
        CarService service = retrofit.create(CarService.class);
        Call<To_information[]> call = service.getTo(String.valueOf(to));
        call.enqueue(new Callback<To_information[]>() {
            @Override
            public void onResponse(Call<To_information[]> call, Response<To_information[]> response) {
                for(int i = 0;i<response.body().length;i++){
                    To_information a = response.body()[i];
                    System.out.println(a.getText());
                    to_information.add(a);
                }
                adapter = new MyAdapter(getBaseContext());
                tolist.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<To_information[]> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return to_information;
    }

    class MyAdapter extends ArrayAdapter<To_information> {
        public MyAdapter(Context context) {
            super(context, R.layout.my_simple_list_to,to);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.my_simple_list_to, null);
            }
            ((CheckBox)convertView.findViewById(R.id.checkBox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            To_information a = getItem(position);
            ((TextView) convertView.findViewById(R.id.to_info_text)).setText(a.getText());
            return convertView;
        }
    }
}
