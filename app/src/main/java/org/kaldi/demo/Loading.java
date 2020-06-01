package org.kaldi.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.victor.loading.rotate.RotateLoading;

import org.kaldi.Assets;
import org.kaldi.Model;
import org.kaldi.Vosk;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;


public class Loading extends AppCompatActivity{

    static {
        System.loadLibrary("kaldi_jni");
    }
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private RotateLoading rotateLoading;
    private RecognizerModel mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        fullWindow();
        rotateLoading = findViewById(R.id.rotateloading);
        rotateLoading.start();
        checkSound();
        new SetupTask(this).execute();
    }
    private void fullWindow(){
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @SuppressLint("StaticFieldLeak")
    class SetupTask extends AsyncTask<Void, Void, Void> {
        WeakReference<Loading> activityReference;

        SetupTask(Loading activity) {
            this.activityReference = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Assets assets = new Assets(activityReference.get());
                File assetDir = assets.syncAssets();
                Log.d("KaldiDemo", "Sync files in the folder " + assetDir.toString());
                Vosk.SetLogLevel(0);
                activityReference.get().mod.model = new Model(assetDir.toString() + "/model-android");
            } catch (IOException ignored) {}
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("onPostExecute выполнен");
            recognizeMicrophone();
            loadNextActivity();
        }
    }
    private void checkSound(){
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
            return;
        }
    }
    private void loadNextActivity(){
        rotateLoading.stop();
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
        finish();
    }
    public void recognizeMicrophone() {
        try {
            RecognizerModel.createRecognizer();
        }catch (Exception e){}
    }
}
