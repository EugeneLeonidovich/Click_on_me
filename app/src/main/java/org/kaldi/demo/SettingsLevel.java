package org.kaldi.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

@SuppressLint("Registered")
class SettingsLevel extends AppCompatActivity {

    private Levels mainActivity;
    private Class<?> secondActivity;
    private Class<?> backActivity;

    private DialogLevel dialogLevel = new DialogLevel(this);
    private SettingsWindow settingsWindow = new SettingsWindow();
    private PressBack pressBack = new PressBack(this);
    private LogicGame locigGame = new LogicGame(this);
    private Window w;

    Levels getMainActivity() {
        return mainActivity;
    }

    Class<?> getSecondActivity() {
        return secondActivity;
    }

    Class<?> getBackActivity() {
        return backActivity;
    }

    SettingsLevel(Levels mainActivity,
                         Class<?> secondActivity,
                         Class<?> backActivity){
        this.mainActivity = mainActivity;
        this.secondActivity = secondActivity;
        this.backActivity = backActivity;
    }

    Dialog getDialogEnd(){
        return dialogLevel.getDialogEnd();
    }
    void setWindow(Window w){
        this.w = w;
    }
    public Window getWindow(){
        return w;
    }


    void setBackground(ImageView img, int res){
        img.setImageResource(res);
    }

    void buttonBack(Button btn_back){
        pressBack.BtnPressBack(btn_back);
    }

    void systemBack(){
        pressBack.btnPressBack();
    }

    void setText_lvl(TextView t, String s, int color){
        t.setText(s);
        t.setTextColor(ContextCompat.getColor((Context) getMainActivity(), color));
    }

    void setSettingsWindow(){
        settingsWindow.blockWindow(w);
        settingsWindow.fullWindow(w);
    }
    void setColorBtn(Button btn, int res, int color){
        btn.setBackgroundResource(res);
        btn.setTextColor(ContextCompat.getColor((Context) getMainActivity(), color));
    }

    void dialogShow(Dialog dialogStart, Dialog dialogEnd,
                    int dialprev, int dialEnd, int idDrowable,
                    int idBackground, int idText, int idEndText){
        dialogStart(dialogStart, dialprev, idDrowable, idText, idBackground);
        dialogEnd(dialogEnd, dialEnd, idEndText, idBackground);
    }

    private void dialogStart(Dialog dialog, int dialprev, int idDrowable,
                             int idText, int idBackground){
        dialogLevel.setDialog(dialog);
        dialogLevel.dialogWindow(dialprev, idDrowable, idText, idBackground);
    }

    private void dialogEnd(Dialog dialog, int dialEnd, int idEndText, int idBackground){
        dialogLevel.setDialogEnd(dialog);
        dialogLevel.dialogWindowEnd(dialEnd, idEndText, idBackground);
    }

    @SuppressLint("ClickableViewAccessibility")
    void ImageLeftRight(final Animation a, final ImageView img_left, final ImageView img_right,
                        final TextView text_left, final TextView text_right, final ArrayList<TextView> mas,
                        final int[] images, final int[] text, final int color) {
        locigGame.LogicLevel(a, img_left, img_right, text_left, text_right, mas, images, text, color);
    }
}
