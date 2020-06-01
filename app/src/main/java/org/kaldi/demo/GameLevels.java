package org.kaldi.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        fullWindow();
        backMainMenu();

        startLevell();
        startLevel2();
        startLevel3();
        startLevel4();
    }

    @Override
    public void onBackPressed(){
        PressedBtnBack();
    }
    private void PressedBtnBack(){
        try{
            Intent intent = new Intent(GameLevels.this, LaunchActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception ignored) {}
    }
    private void fullWindow(){
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void backMainMenu(){
        Button btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PressedBtnBack();
            }
        });
    }

    private void startLevell(){
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){}
            }
        });
    }
    private void startLevel2(){
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){}
            }
        });
    }
    private void startLevel3() {
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                }
            }
        });
    }
    private void startLevel4() {
        TextView textView3 = findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level4.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                }
            }
        });
    }
}
