package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v)
    {
        EditText etNum1 = findViewById(R.id.etNum1);
        EditText etNum2 = findViewById(R.id.etNum1);
        TextView tvResult = findViewById(R.id.tvResult);

        String num1 = etNum1.getText().toString();
        String num2 = etNum2.getText().toString();

        if(!num1.isEmpty() && !num2.isEmpty())
        {
            tvResult.setText(String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2)));
        }
        else
        {
            tvResult.setText("");
        }
    }
}
