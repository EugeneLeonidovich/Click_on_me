package org.kaldi.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

class LogicGame extends AppCompatActivity {

    private SettingsLevel settings;
    private int numLeft, numRight;
    private int count=0;
    private Random random = new Random();

    LogicGame(SettingsLevel settings) {
        this.settings = settings;
    }

    @SuppressLint({"ClickableViewAccessibility", "ResourceAsColor"})
    void LogicLevel(final Animation a, final ImageView img_left, final ImageView img_right,
                    final TextView text_left, final TextView text_right,
                    final ArrayList<TextView> mas,
                    final int[] images, final int[] text, final int color){
        //Скругление углов
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);

        text_left.setTextColor(ContextCompat.getColor((Context) settings.getMainActivity(), color));
        text_right.setTextColor(ContextCompat.getColor((Context) settings.getMainActivity(), color));

        //Генерация и установка картинок
        numLeft = random.nextInt(images.length);
        img_left.setImageResource(images[numLeft]);
        assert text != null;
        text_left.setText(text[numLeft]);

        numRight = random.nextInt(images.length);

        while(numLeft == numRight)
            numRight = random.nextInt(images.length);

        img_right.setImageResource(images[numRight]);
        text_right.setText(text[numRight]);

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
                            mas.get(i).setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            mas.get(i).setBackgroundResource(R.drawable.style_points_green);
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
                            mas.get(i).setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            mas.get(i).setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        settings.getDialogEnd().show();
                    }else{
                        numLeft = random.nextInt(images.length);
                        img_left.setImageResource(images[numLeft]);
                        text_left.setText(text[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(images.length);

                        while(numLeft == numRight)
                            numRight = random.nextInt(images.length);

                        img_right.setImageResource(images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(text[numRight]);

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
                            mas.get(i).setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            mas.get(i).setBackgroundResource(R.drawable.style_points_green);
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
                            mas.get(i).setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count; i++) {
                            mas.get(i).setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){
                        settings.getDialogEnd().show();
                    }else{
                        numLeft = random.nextInt(images.length);
                        img_left.setImageResource(images[numLeft]);
                        text_left.setText(text[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(images.length);

                        while(numLeft == numRight)
                            numRight = random.nextInt(images.length);

                        img_right.setImageResource(images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(text[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
        System.out.println(count);
    }

}
