package com.example.autoavto.ui.main;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToActivity extends AppCompatActivity {
ListView view;
    List<To_information> to = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
        ArrayAdapter<To_information> adapter = new MyAdapter(this);
        view = findViewById(R.id.listTo);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2:8452").build();
        CarService service = retrofit.create(CarService.class);
        System.out.println(getIntent().getStringExtra("to"));
        Call<To_information[]> call = service.getTo(getIntent().getStringExtra("to"));
        call.enqueue(new Callback<To_information[]>() {
            @Override
            public void onResponse(Call<To_information[]> call, Response<To_information[]> response) {
                for(int i =0;i<response.body().length;i++){
                    To_information a = response.body()[i];
                    to.add(a);
                }

                view.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<To_information[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
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