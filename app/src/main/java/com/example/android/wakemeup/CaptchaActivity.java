package com.example.android.wakemeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class CaptchaActivity extends AppCompatActivity {

    // random number for captcha image choice
    private int randomNumber;

    // min of random number
    private static final int min = 0;

    //max of random nmber
    private static final int max = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        generateCaptchaImage();
    }

    public void checkButtonClick(View view) {

        EditText input = (EditText) findViewById(R.id.captcha_user_text);
        String captchaText = input.getText().toString();

        String[] captchaList = getResources().getStringArray(R.array.captcha_array);

        if (captchaList[randomNumber].equals(captchaText)) {

            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, intent);
            finish();

        } else {

            input.setText("");
            Toast.makeText(this, R.string.oops_try_again, Toast.LENGTH_SHORT).show();
            generateCaptchaImage();
        }
    }

    private void generateCaptchaImage() {

        // captcha choice generation
        Random r = new Random();
        randomNumber = r.nextInt(max - min + 1) + min;

        ImageView captchaIcon = (ImageView) findViewById(R.id.captcha_icon);

        int[] captchaIdsList = new int[]{
                R.drawable.captcha1,
                R.drawable.captcha2,
                R.drawable.captcha3,
                R.drawable.captcha4,
                R.drawable.captcha5,
                R.drawable.captcha6,
                R.drawable.captcha7,
                R.drawable.captcha8,
                R.drawable.captcha9,
                R.drawable.captcha10
        };

        // default choice
        captchaIcon.setImageResource(R.drawable.captcha1);

        if(captchaIdsList.length > randomNumber) {
            captchaIcon.setImageResource(captchaIdsList[randomNumber]);
        }
    }
}
