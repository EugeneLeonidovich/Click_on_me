package org.kaldi.demo;

import android.annotation.SuppressLint;
import org.kaldi.Model;
import org.kaldi.SpeechRecognizer;
import java.io.IOException;

@SuppressLint("Registered")
class RecognizerModel {
    static Model model;
    static SpeechRecognizer recognizer;
    static void createRecognizer() throws IOException {
        recognizer = new SpeechRecognizer(model);
    }
}
