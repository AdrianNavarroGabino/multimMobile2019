package com.example.salutator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void greet(View v)
    {
        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();
        if(name.isEmpty())
        {
            Toast.makeText(this,
                    "Please, enter your name",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,
                    "Hello, " + name,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
