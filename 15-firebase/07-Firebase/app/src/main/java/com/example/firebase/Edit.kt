package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebase.MainActivity.Companion.db
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Edit : AppCompatActivity() {

    var modificable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        idET.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val usersCollection: CollectionReference = db.collection("users")

                val docRef: DocumentReference = usersCollection.document(idET.text.toString())

                docRef.get().apply {
                    addOnSuccessListener {
                        if(!it["login"].toString().equals("null")) {
                            loginET.setText(it["login"].toString())
                            nameEt.setText(it["name"].toString())
                            lastloginET.setText((it["lastlogin"] as Timestamp).toDate().toString())
                            logintimesET.setText(it["logintimes"].toString())
                            modificable = true
                        }
                        else
                        {
                            loginET.text.clear()
                            nameEt.text.clear()
                            lastloginET.text.clear()
                            logintimesET.text.clear()
                            modificable = false
                            Toast.makeText(
                                applicationContext, "User not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        editBtn.setOnClickListener {
            if(loginET.text.isNotEmpty() && nameEt.text.isNotEmpty() &&
                lastloginET.text.isNotEmpty() && logintimesET.text.isNotEmpty() && modificable)
            {
                val refUpdTopic = db.collection("users").document(idET.text.toString())

                refUpdTopic
                    .update(mapOf(
                        "login" to loginET.text.toString(),
                        "name" to nameEt.text.toString(),
                        "lastlogin" to Timestamp(Date(lastloginET.text.toString().replace("-", "/"))),
                        "logintimes" to Integer.parseInt(logintimesET.text.toString())
                    ))
                    .addOnSuccessListener {
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
            }
            else if(!modificable)
            {
                Toast.makeText(
                    applicationContext, "Choose an user to modify", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(
                    applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
