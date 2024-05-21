package com.example.MyClub.Windows;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.MyClub.Interfaces.DialogListener;
import com.example.conectarapi.R;

public class DialogWindows {


    public  void acceptCancelWindow(Context context, String title,String message, DialogListener dialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogListener != null) {
                            dialogListener.onApceptSelected();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialogListener.onCancelSelected();
                    }
                })
                .setCancelable(true) // Evita que se cierre el diálogo al tocar fuera de él
                .show();
    }




}
