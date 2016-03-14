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

    int generatedCaptcha;
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        generateCaptchaImage();
    }

    private void generateCaptchaImage(){

        int min = 0;
        int max = 9;

        Random r = new Random();
        randomNumber = r.nextInt(max - min + 1) + min;

        ImageView captchaIcon = (ImageView)findViewById(R.id.captcha_icon);

        switch (randomNumber){

            case 0: generatedCaptcha = R.drawable.captcha1;
                break;
            case 1: generatedCaptcha = R.drawable.captcha2;
                break;
            case 2: generatedCaptcha = R.drawable.captcha3;
                break;
            case 3: generatedCaptcha = R.drawable.captcha4;
                break;
            case 4: generatedCaptcha = R.drawable.captcha5;
                break;
            case 5: generatedCaptcha = R.drawable.captcha6;
                break;
            case 6: generatedCaptcha = R.drawable.captcha7;
                break;
            case 7: generatedCaptcha = R.drawable.captcha8;
                break;
            case 8: generatedCaptcha = R.drawable.captcha9;
                break;
            case 9: generatedCaptcha = R.drawable.captcha10;
                break;
            default:generatedCaptcha = R.drawable.captcha1;
                break;
        }

        captchaIcon.setImageResource(generatedCaptcha);
    }

    public void checkButtonClick(View view) {

        EditText input = (EditText)findViewById(R.id.captcha_user_text);
        String captchaText = input.getText().toString();

        String[] captchaList = getResources().getStringArray(R.array.captcha_array);

        if(captchaList[randomNumber].equals(captchaText)){

            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        }
        else {

            input.setText("");
            Toast.makeText(this, R.string.oops_try_again, Toast.LENGTH_SHORT).show();
            generateCaptchaImage();
        }

    }
}
