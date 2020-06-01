package org.kaldi.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class DialogLevel {

    private SettingsLevel settings;
    private Dialog dialog;
    private Dialog dialogEnd;
    private PressBack back;

    DialogLevel(SettingsLevel settings) {
        this.settings = settings;
    }

    void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    void setDialogEnd(Dialog dialogEnd) {
        this.dialogEnd = dialogEnd;
    }

    Dialog getDialog() {
        return dialog;
    }

    Dialog getDialogEnd() {
        return dialogEnd;
    }

    void dialogWindow(int dialPrev, int idDrowable, int idText, int idBackground){
        //Вызов диалогового окна
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрытие заголовка
        dialog.setContentView(dialPrev);//Путь к диалоговому окну
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад

        LinearLayout fon = dialog.findViewById(R.id.dialogfon);
        fon.setBackgroundResource(idBackground);

        ImageView previewimg = dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(idDrowable);

        TextView textdescription = dialog.findViewById(R.id.textdescriptionstart);
        textdescription.setText(idText);

        //Кнопка закрытия диалогового окна
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//Закрытие диалогового окна
            }
        });
        //Кнопка продолжить
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//Закрытие диалогового окна
            }
        });
        dialog.show(); //Показать диалоговое окно

    }
    void dialogWindowEnd(int dialEnd, int idEndText, int idBackground){
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрытие заголовка
        dialogEnd.setContentView(dialEnd);//Путь к диалоговому окну
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна

        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой назад

        LinearLayout fon = dialog.findViewById(R.id.dialogfon);
        fon.setBackgroundResource(idBackground);

        TextView textdescription = dialogEnd.findViewById(R.id.textdescriptionend);
        textdescription.setText(idEndText);

        //Кнопка закрытия диалогового окна
        TextView btnclose2 = dialogEnd.findViewById(R.id.btncloseend);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Context) settings.getMainActivity(), settings.getBackActivity());
                ((Context) settings.getMainActivity()).startActivity(intent);
                settings.getMainActivity().end();
                dialogEnd.dismiss();//Закрытие диалогового окна
            }
        });
        //Кнопка продолжить
        Button btncontinue2 = dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent((Context) settings.getMainActivity(), settings.getSecondActivity());
                    ((Context) settings.getMainActivity()).startActivity(intent);
                    settings.getMainActivity().end();
                }catch (Exception ignored){}
                dialogEnd.dismiss();//Закрытие диалогового окна
            }
        });
    }
}
