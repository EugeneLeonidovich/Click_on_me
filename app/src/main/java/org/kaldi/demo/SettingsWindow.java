package org.kaldi.demo;

import android.view.Window;
import android.view.WindowManager;

class SettingsWindow {
    void fullWindow(Window w){
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    void blockWindow(Window w){
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }
}
