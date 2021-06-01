package com.example.autoavto.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.autoavto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    FloatingActionButton fab;
    ListView listcars;
    View root;
    private static final List<CarNames> car = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        fab = root.findViewById(R.id.fabcreate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), CreateCarActivity.class);
                startActivity(i);
            }
        });
        listcars = root.findViewById(R.id.listOfCars);
        listcars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i2 = new Intent(root.getContext(),CarToActivity.class);
                i2.putExtra("namecar",car.get(i).getCarName());
                startActivity(i2);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        car.clear();
        File file = new File("data/data/com.example.autoavto/cache/");
        String[] files = file.list();
        for(int i = 0;i< files.length;i++){
            CarNames names = new CarNames();
            names.setCarName(files[i].replace(".txt",""));
            car.add(names);
        }
        ArrayAdapter<CarNames> ad = new CarAdapter(root.getContext());
        listcars.setAdapter(ad);
        super.onResume();
    }

    public class CarAdapter extends ArrayAdapter<CarNames> {


        public CarAdapter(Context context) {
            super(context, R.layout.my_simple_list_cars, car);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_simple_list_cars, null);
            }

            CarNames a = getItem(position);
            String b = a.getCarName();
            TextView text = convertView.findViewById(R.id.textcar);
            text.setText(b);
            return convertView;
        }
    }
}