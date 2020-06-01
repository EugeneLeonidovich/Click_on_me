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

public class Level3 extends AppCompatActivity implements RecognitionListener, Levels {

    private SettingsLevel lvlThree = new SettingsLevel(this, Level4.class, GameLevels.class);
    private StringElement stringElement = new StringElement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        recognizeMicrophone();

        ArrayList<TextView> mas = arr(Array.progress);

        lvlThree.setWindow(getWindow());
        lvlThree.setSettingsWindow();

        lvlThree.setText_lvl((TextView)findViewById(R.id.text_levels),
                getString(R.string.level3),R.color.black95);

        lvlThree.setColorBtn((Button)findViewById(R.id.button_back),
                R.drawable.style_btn_stroke_black95,
                R.color.black95);

        lvlThree.setBackground((ImageView)findViewById(R.id.background), R.drawable.level3);

        lvlThree.dialogShow(new Dialog(this),new Dialog(this),
                R.layout.previewdialog, R.layout.dialogend,
                R.drawable.previewimgthree, R.drawable.previevbackground3,
                R.string.levelthree, R.string.leveltwoEnd);

        lvlThree.buttonBack((Button)findViewById(R.id.button_back));

        lvlThree.ImageLeftRight(AnimationUtils.loadAnimation(this, R.anim.alpha),
                (ImageView)findViewById(R.id.img_left), (ImageView)findViewById(R.id.img_right),
                (TextView)findViewById(R.id.textleft), (TextView)findViewById(R.id.textright),
                mas, Array.images3, Array.texts3, R.color.black95);
        startListening();

    }

    @Override
    public void end(){
        stopListening();
        finish();
    }

    @Override
    public void onBackPressed(){
        lvlThree.systemBack();
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
    private void startListening(){
        System.out.println("Запись начатая");
        RecognizerModel.recognizer.startListening();
    }
    private void stopListening(){
        System.out.println("Запись остановленная");
        RecognizerModel.recognizer.cancel();
    }
    private void recognizeMicrophone() {
        try {
            RecognizerModel.recognizer.addListener(this);
        }catch (Exception ignored){}
    }
}
