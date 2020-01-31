package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        showBtn.setOnClickListener {
            /*var datos: String = ""
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
                }*/

            resultTV.text = ""
            val users: CollectionReference = db.collection("users")

            users.get().apply {
                addOnSuccessListener {
                    for(user in it) {
                        resultTV.append(user!!["login"].toString() + " " + user!!["name"].toString() + "\n")
                    }
                }
            }
        }

        addBtn.setOnClickListener {
            if(idET.text.isNotEmpty() && loginET.text.isNotEmpty() && nameEt.text.isNotEmpty())
            {
                val user = hashMapOf(
                    "id" to idET.text.toString(),
                    "login" to loginET.text.toString(),
                    "name" to nameEt.text.toString()
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { _ ->
                        resultTV.text = "User added"
                        idET.text.clear()
                        loginET.text.clear()
                        nameEt.text.clear()
                    }
                    .addOnFailureListener{ exception ->
                        resultTV.text = "Error adding user"
                    }
            }
        }
    }
}
