package com.example.autoavto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;
import java.io.File;

    public class MyDialogFragment extends DialogFragment {

        @NonNull
        @NotNull
        @Override
        public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            String title = getResources().getString(R.string.dialog_title);
            String message = getResources().getString(R.string.dialog_deleteNote_sub_title);
            String buttonAccept = getResources().getString(R.string.dialog_button_accept);
            String buttonCancel = getResources().getString(R.string.dialog_button_disaccept);

            String filepath = getArguments().getString("name");


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);  // заголовок
            builder.setMessage(message); // сообщение
            builder.setPositiveButton(buttonAccept, (dialog, id) -> {
                System.out.println(filepath);
                File file = new File(filepath);
                if (file.delete()) {
                    Toast.makeText(getContext(), "Заметка удалена", Toast.LENGTH_SHORT).show();
                }
                getActivity().finish();
            });
            builder.setNegativeButton(buttonCancel, (dialog, id) -> {

            });
            builder.setCancelable(true);
            return builder.create();
        }
}
