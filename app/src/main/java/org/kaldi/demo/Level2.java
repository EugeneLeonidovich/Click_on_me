package org.kaldi.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.kaldi.RecognitionListener;

import java.util.ArrayList;

public class Level2 extends AppCompatActivity implements RecognitionListener, Levels {

    private SettingsLevel lvlTwo = new SettingsLevel(this, Level3.class, GameLevels.class);
    private StringElement stringElement = new StringElement();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        try {
            recognizeMicrophone();
            ArrayList<TextView> mas = arr(Array.progress);

            lvlTwo.setText_lvl((TextView)findViewById(R.id.text_levels),
                    getString(R.string.level2), R.color.white);

            lvlTwo.setBackground((ImageView)findViewById(R.id.background), R.drawable.level1);

            lvlTwo.dialogShow(new Dialog(this),new Dialog(this),
                    R.layout.previewdialog, R.layout.dialogend,
                    R.drawable.previewimgtwo,R.drawable.previevbackgroundone,
                    R.string.leveltwo, R.string.leveltwoEnd);

            lvlTwo.setWindow(getWindow());
            lvlTwo.setSettingsWindow();

            lvlTwo.buttonBack((Button)findViewById(R.id.button_back));

            lvlTwo.ImageLeftRight(AnimationUtils.loadAnimation(this, R.anim.alpha),
                    (ImageView)findViewById(R.id.img_left), (ImageView)findViewById(R.id.img_right),
                    (TextView)findViewById(R.id.textleft), (TextView)findViewById(R.id.textright),
                    mas, Array.images2, Array.texts2, R.color.white);
            startListening();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void end(){
        stopListening();
        finish();
    }

    @Override
    public void onBackPressed(){
        lvlTwo.systemBack();
    }

    private ArrayList<TextView> arr(int[] mass){
        ArrayList<TextView> txt = new ArrayList<>();
        for (int value : mass) {
            txt.add((TextView) findViewById(value));
        }
        return txt;
    }

    public void simulateEventUpDown(ImageView act, float x, float y) {
        MotionEvent e = MotionEvent.obtain( 1000,1000,
                MotionEvent.ACTION_DOWN,
                x, y, 0);
        MotionEvent e1 = MotionEvent.obtain( 1000,1000,
                MotionEvent.ACTION_UP,
                x, y, 0);
        act.dispatchTouchEvent(e);
        System.out.println("Нажато");
        act.dispatchTouchEvent(e1);
        System.out.println("Отпущено");
    }

    @Override
    public void onResult(String hypothesis) {
        try{
            if (!stringElement.checkStr(hypothesis)){
                int stroke = stringElement.getStr(hypothesis);
                System.out.println(hypothesis);
                if (stroke==1) {
                    simulateEventUpDown((ImageView)findViewById(R.id.img_left),
                            Position.leftX, Position.leftY);
                    System.out.println(hypothesis);
                } else if (stroke==2) {
                    simulateEventUpDown((ImageView)findViewById(R.id.img_right),
                            Position.rightX, Position.rightY);
                    System.out.println(hypothesis);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPartialResult(String hypothesis){}
    @Override
    public void onError(Exception e) {}
    @Override
    public void onTimeout() {}

    public void startListening(){
        System.out.println("Запись начатая");
        RecognizerModel.recognizer.startListening();
    }
    public void stopListening(){
        System.out.println("Запись остановленная");
        RecognizerModel.recognizer.cancel();
    }
    public void recognizeMicrophone() {
        try {
            RecognizerModel.recognizer.addListener(this);
        }catch (Exception ignored){}
    }
}
