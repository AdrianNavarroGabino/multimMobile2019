package com.example.md5orsha1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_encrypt.*
import java.security.MessageDigest


class Encrypt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypt)

        acceptBtn.setOnClickListener {
            var result: String? = null;

            if(MD5Rb.isChecked || SHA1Rb.isChecked)
            {
                if(MD5Rb.isChecked)
                {
                    result = md5(intent.getStringExtra("inputText"))
                }
                else
                {
                    result = sha1(intent.getStringExtra("inputText"))
                }

                val intentResult: Intent = Intent().apply {
                    putExtra("encoded", result)
                }

                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }
    }

    fun sha1(input: String) = hashString("SHA-1", input)
    fun md5(input: String) = hashString("MD5", input)

    private fun hashString(type: String, input: String): String {
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        return printHexBinary(bytes).toUpperCase()
    }

    val HEX_CHARS = "0123456789ABCDEF".toCharArray()

    fun printHexBinary(data: ByteArray): String {
        val r = StringBuilder(data.size * 2)
        data.forEach { b ->
            val i = b.toInt()
            r.append(HEX_CHARS[i shr 4 and 0xF])
            r.append(HEX_CHARS[i and 0xF])
        }
        return r.toString()
    }
}
