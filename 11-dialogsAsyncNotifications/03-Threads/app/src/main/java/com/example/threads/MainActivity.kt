package com.example.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar2.isGone = true

        startBtn.setOnClickListener {
            progressBar2.isGone = false
            var progressBarStatus = 0
            var section = 0

            Thread(

                Runnable {
                    while (progressBarStatus < 100) {

                        try {
                            section += 5
                            Thread.sleep(250)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        progressBarStatus = section
                        progressBar.progress = progressBarStatus
                    }

                    runOnUiThread {
                        progressBar2.isGone = true
                        progressBar.progress = 0
                    }
                }
            ).start()
        }
    }
}
