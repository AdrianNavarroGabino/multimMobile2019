package com.example.multiplicationgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int num1Aux;
    int num2Aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView etNumber1 = findViewById(R.id.num1);
        TextView etNumber2 = findViewById(R.id.num2);

        Random rn = new Random();
        num1Aux = rn.nextInt(10) + 1;
        num2Aux = rn.nextInt(10) + 1;

        etNumber1.setText(String.valueOf(num1Aux));
        etNumber2.setText(String.valueOf(num2Aux));
    }

    public void check(View v)
    {
        EditText result = findViewById(R.id.result);

        if(num1Aux * num2Aux == Integer.parseInt(result.getText().toString()))
        {
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Wrong...", Toast.LENGTH_SHORT).show();
        }
    }
}
