package com.example.autoavto.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.autoavto.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToActivity extends AppCompatActivity {
ListView view;
    public static List<To_information> to = new ArrayList<>();
    TextView carto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
        ArrayAdapter<To_information> adapter = new MyAdapter(this);
        view = findViewById(R.id.listTo);
        carto = findViewById(R.id.textViewTo);
        carto.setText("ТО - "+ (Integer.parseInt(getIntent().getStringExtra("to"))+1));
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.0.158:8452").build();
        CarService service = retrofit.create(CarService.class);
        Call<To_information[]> call = service.getTo(getIntent().getStringExtra("to"),getIntent().getStringExtra("carname"));
        call.enqueue(new Callback<To_information[]>() {
            @Override
            public void onResponse(@NotNull Call<To_information[]> call, @NotNull Response<To_information[]> response) {
                to.clear();
                assert response.body() != null;
                Collections.addAll(to, response.body());
                view.setAdapter(adapter);
            }
            @Override
            public void onFailure(@NotNull Call<To_information[]> call, @NotNull Throwable t) {
                Toast.makeText(ToActivity.this, "Проверьте ваше подключение к интернету и перезайдите!", Toast.LENGTH_LONG).show();
            }
        });

        view.setOnItemClickListener((parent, view, position, id) -> {
            To_information a = to.get(position);
            if(a.getArticule()!=null&&!a.getArticule().isEmpty()){
                FragmentManager manager = getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putInt("position", position);
                DialogFragment DialogFragment = new DialogFragment();
                DialogFragment.setArguments(args);
                DialogFragment.show(manager, "Dialog");
            }
        });
    }
    static class MyAdapter extends ArrayAdapter<To_information> {
        public MyAdapter(Context context) {
            super(context, R.layout.my_simple_list_to,to);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.my_simple_list_to, null);
            }
            ((CheckBox)convertView.findViewById(R.id.checkBox)).setOnCheckedChangeListener((compoundButton, b) -> {

            });
            To_information a = getItem(position);
            ((TextView) convertView.findViewById(R.id.to_info_text)).setText(a.getText());
            return convertView;
        }

    }
    public static class DialogFragment extends androidx.fragment.app.DialogFragment{
        @NonNull
        @NotNull
        @Override
        public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            String title = getResources().getString(R.string.dialog_title);
            String message = getResources().getString(R.string.dialog_create_url_browser);
            String buttonAccept = getResources().getString(R.string.dialog_button_accept);
            String buttonCancel = getResources().getString(R.string.dialog_button_disaccept);
            assert getArguments() != null;
            int position = getArguments().getInt("position");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);  // заголовок
            builder.setMessage(message); // сообщение
            builder.setPositiveButton(buttonAccept, (dialog, id) -> {
                To_information a = to.get(position);
                String uri = "https://www.autodoc.ru/man/647/part/"+a.getArticule();
                Uri address = Uri.parse(uri);
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
            });
            builder.setNegativeButton(buttonCancel, (dialog, id) -> {

            });
            builder.setCancelable(true);
            return builder.create();
        }
    }
}