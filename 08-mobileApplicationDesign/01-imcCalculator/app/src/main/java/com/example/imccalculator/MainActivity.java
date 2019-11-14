package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v)
    {
        EditText etHeight = findViewById(R.id.heightEt);
        EditText etWeight = findViewById(R.id.weightEt);

        if(!etWeight.getText().toString().isEmpty() && !etHeight.getText().toString().isEmpty()) {
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }

            double imcResult = 10000 * Double.parseDouble(etWeight.getText().toString()) /
                    Double.parseDouble(etHeight.getText().toString()) /
                    Double.parseDouble(etHeight.getText().toString());

            TextView resultLbl = findViewById(R.id.resultLbl);
            resultLbl.setText(String.valueOf(imcResult));
            TextView resultLbl2 = findViewById(R.id.resultLbl2);

            if(imcResult > 25)
                resultLbl2.setText("Too fat");
            else if(imcResult < 19)
                resultLbl2.setText("Too skinny");
            else
                resultLbl2.setText("Perfect weight");
        }
        else
        {
            Toast.makeText(this, "Enter height and weight", Toast.LENGTH_SHORT).show();
        }
    }
}
