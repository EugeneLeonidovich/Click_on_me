package org.kaldi.demo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class PressBack {

    private SettingsLevel settings;

    PressBack(SettingsLevel settings) {
        this.settings = settings;
    }

    void btnPressBack(){
        try{
            Intent intent = new Intent((Context) settings.getMainActivity(), settings.getBackActivity());
            ((Context) settings.getMainActivity()).startActivity(intent);
            settings.getMainActivity().end();
        }catch (Exception ignored){}
    }
    void BtnPressBack(Button btn_back){
        //Кнопка назад
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPressBack();
            }
        });
    }
    // Разобраться в чем проблема (возможно)
    void BtnPressBackEnd(TextView btnclose){
        //Кнопка назад
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPressBack();
            }
        });
    }

}
