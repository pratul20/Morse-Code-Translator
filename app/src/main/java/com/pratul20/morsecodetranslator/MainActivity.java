package com.pratul20.morsecodetranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    HashMap <Character,String> charMap;
    ConstraintLayout MainLayout;
    EditText normalText;
    EditText morseText;

    public char getKeyByValue(String value) {
        for (Map.Entry<Character, String> entry : charMap.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return 0;
    }

    void mapToChar() {
        charMap.put('A',".-");
        charMap.put('B',"-...");
        charMap.put('C',"-.-.");
        charMap.put('D',"-..");
        charMap.put('E',".");
        charMap.put('F',"..-.");
        charMap.put('G',"--.");
        charMap.put('H',"....");
        charMap.put('I',"..");
        charMap.put('J',".---");
        charMap.put('K',"-.-");
        charMap.put('L',".-..");
        charMap.put('M',"--");
        charMap.put('N',"-.");
        charMap.put('O',"---");
        charMap.put('P',".--.");
        charMap.put('Q',"--.-");
        charMap.put('R',".-.");
        charMap.put('S',"...");
        charMap.put('T',"-");
        charMap.put('U',"..-");
        charMap.put('V',"...-");
        charMap.put('W',".--");
        charMap.put('X',"-..-");
        charMap.put('Y',"-.--");
        charMap.put('Z',"--..");
        charMap.put('0',"-----");
        charMap.put('1',".----");
        charMap.put('2',"..---");
        charMap.put('3',"...--");
        charMap.put('4',"....-");
        charMap.put('5',".....");
        charMap.put('6',"-....");
        charMap.put('7',"--...");
        charMap.put('8',"---..");
        charMap.put('9',"----.");
        charMap.put('.',".-.-.-");
        charMap.put(',',"--..--");
        charMap.put('?',"..--..");
        charMap.put('\'',".----.");
        charMap.put('!',"-.-.--");
        charMap.put('/',"-..-.");
        charMap.put('(',"-.--.");
        charMap.put(')',"-.--.-");
        charMap.put('&',".-...");
        charMap.put(':',"---...");
        charMap.put(';',"-.-.-.");
        charMap.put('=',"-...-");
        charMap.put('+',".-.-.");
        charMap.put('-',"-....-");
        charMap.put('_',"..--.-");
        charMap.put('\"',".-..-.");
        charMap.put('$',"...-..-");
        charMap.put('@',".--.-.");
        charMap.put(' ',"/");
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public void convertMorse(View view) {
        String normal = normalText.getText().toString();
        if(normal.length()==0) {
            return;
        }
        normal = normal.toUpperCase();
        String morse = "";
        for(int i=0;i<normal.length();i++) {
            if(charMap.containsKey(normal.charAt(i))) {
                morse+=charMap.get(normal.charAt(i));
                morse+=" ";
            }
            else {
                morseText.setText("Your Text Contains a character which cannot be translated to Morse Code!");
                return;
            }
        }
        morseText.setText(morse);

        hideSoftKeyboard(this);


    }

    public void convertNormal(View view) {
        String morse = morseText.getText().toString();
        if(morse.length()==0) {
            return;
        }
        String normal = "";
        String[] codes = morse.split(" ");
        for (String code : codes) {
            if (charMap.containsValue(code)) {
                normal += getKeyByValue(code);
            } else {
                normalText.setText("Invalid Morse Code Entered!");
                return;
            }
        }
        normalText.setText(normal);

        hideSoftKeyboard(this);

    }

    public void deleteNormal(View view) {
        normalText.setText("");
    }

    public void deleteMorse(View view) {
        morseText.setText("");
    }

    public void copyNormal(View view) {
        String normal = normalText.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("normal", normal);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    public void copyMorse(View view) {
        String morse = morseText.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("morse", morse);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        normalText = findViewById(R.id.normalText);
        morseText = findViewById(R.id.morseText);
        MainLayout = findViewById(R.id.MainLayout);
        charMap = new HashMap<Character, String>();
        mapToChar();
    }
}