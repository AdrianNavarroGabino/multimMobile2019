package com.example.classschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text: String = ""

        accept.setOnClickListener {
            if(morning.isChecked || afternoon.isChecked)
            {
                text += if(morning.isChecked) "Morning" else "Afternoon"
                text += " turn\n";

                if(dataAccess.isChecked)
                {
                    text += "Data Access\n"
                }
                if(eie.isChecked)
                {
                    text += "EIE\n"
                }
                if(psp.isChecked)
                {
                    text += "PSP\n"
                }
                if(mobile.isChecked)
                {
                    text += "Mobile Devices\n"
                }
                if(interfaces.isChecked)
                {
                    text += "Interfaces\n"
                }

                result.text = text;
            }
        }
    }


}
