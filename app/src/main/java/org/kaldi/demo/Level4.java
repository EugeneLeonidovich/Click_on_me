package org.kaldi.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level4 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft, numRight, count=0;
    Array array = new Array();
    Random random = new Random();
    final int[] progress = {
            R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
            R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
            R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
            R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        setText_lvl();
        blockWindow();
        dialogWindow();
        dialogWindowEnd();
        activeBtnPressBack();
        ImageLeftRight();

    }
    //Возврат системной кнопкой
    @Override
    public void onBackPressed(){
        btnPressBack();
    }
    @SuppressLint("ResourceAsColor")
    private void setText_lvl(){
        TextView text_level = findViewById(R.id.text_levels);
        text_level.setText(R.string.level2);
        text_level.setTextColor(getResources().getColor(R.color.blue));

    }
    @SuppressLint("ClickableViewAccessibility")
    private void ImageLeftRight(){
        final Animation a = AnimationUtils.loadAnimation(Level4.this, R.anim.alpha);
        final ImageView img_left = findViewById(R.id.img_left);
        final ImageView img_right = findViewById(R.id.img_right);
        final TextView text_left = findViewById(R.id.textleft);
        final TextView text_right = findViewById(R.id.textright);

        //Скругление углов
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        //Генерация и установка картинок
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);
        text_left.setText(array.texts2[numLeft]);

        numRight = random.nextInt(10);

        while(numLeft == numRight)
            numRight = random.nextInt(10);

        img_right.setImageResource(array.images2[numRight]);
        text_right.setText(array.texts2[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if(numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeft>numRight){
                        if(count<20){
                            count += 1;
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count-=2;
                            }
                        }
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        text_left.setText(array.texts2[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);

                        while(numLeft == numRight)
                            numRight = random.nextInt(10);

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if(numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeft<numRight){
                        if(count<20){
                            count += 1;
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count-=2;
                            }
                        }
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        text_left.setText(array.texts2[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);

                        while(numLeft == numRight)
                            numRight = random.nextInt(10);

                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }
    private void blockWindow(){
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    private void activeBtnPressBack(){
        //Кнопка назад
        Button btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPressBack();
            }
        });
    }
    private void dialogWindow(){
        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрытие заголовка
        dialog.setContentView(R.layout.previewdialog);//Путь к диалоговому окну
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад
        ImageView previewimg = dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgthree);

        //Установка фона диалогового окна
        LinearLayout dialogfon = dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previevbackground3);
        //

        TextView textdescription = dialog.findViewById(R.id.textdescriptionstart);
        textdescription.setText(R.string.levelthree);

        //Кнопка закрытия диалогового окна
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPressBack();
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
    private void dialogWindowEnd(){
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрытие заголовка
        dialogEnd.setContentView(R.layout.dialogend);//Путь к диалоговому окну
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой назад

        TextView textdescriptionEnd = dialogEnd.findViewById(R.id.textdescriptionend);
        textdescriptionEnd.setText(R.string.leveltwoEnd);


        //Кнопка закрытия диалогового окна
        TextView btnclose2 = dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPressBack();
                dialogEnd.dismiss();//Закрытие диалогового окна
            }
        });
        //Кнопка продолжить
        Button btncontinue2 = dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level4.this, Level4.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){}
                dialogEnd.dismiss();//Закрытие диалогового окна
            }
        });
    }
    private void btnPressBack(){
        try{
            Intent intent = new Intent(Level4.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception ignored){}
    }
}
