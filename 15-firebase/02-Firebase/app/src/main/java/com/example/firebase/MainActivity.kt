package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        showBtn.setOnClickListener {
            var datos: String = ""
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for(document in result) {
                        datos += "${document.id} => ${document.data}" + "\n"
                    }

                    resultTV.text = datos
                }
                .addOnFailureListener { exception ->
                    resultTV.text = "Error reading  data"
                }
        }

        addBtn.setOnClickListener {
            if(idET.text.isNotEmpty() && loginET.text.isNotEmpty() && nameEt.text.isNotEmpty())
            {
                val user = hashMapOf(
                    "id" to idET.text,
                    "login" to loginET.text,
                    "name" to nameEt.text
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { _ ->
                        resultTV.text = "User added"
                    }
                    .addOnFailureListener{ exception ->
                        resultTV.text = "Error adding user"
                    }
            }
        }
    }
}
