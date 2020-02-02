package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                        resultTV.append(user!!["login"].toString() + " " +
                                user!!["name"].toString() + " - " +
                                (user!!["lastlogin"] as Timestamp).toDate() +
                                " " + user["logintimes"].toString() + "\n")
                    }
                }
            }
        }

        addBtn.setOnClickListener {
            if(idET.text.isNotEmpty() && loginET.text.isNotEmpty() && nameEt.text.isNotEmpty())
            {
                val user = hashMapOf(
                    "login" to loginET.text.toString(),
                    "name" to nameEt.text.toString(),
                    "lastlogin" to Timestamp(Date(lastloginET.text.toString().replace("-", "/"))),
                    "logintimes" to Integer.parseInt(logintimesET.text.toString())
                )

                db.collection("users")
                    .document(idET.text.toString()).set(user)
                    .addOnSuccessListener { _ ->
                        resultTV.text = "User added"
                        idET.text.clear()
                        loginET.text.clear()
                        nameEt.text.clear()
                        logintimesET.text.clear()
                        lastloginET.text.clear()
                    }
                    .addOnFailureListener{ _ ->
                        resultTV.text = "Error adding user"
                    }
            }
        }

        searchBtn.setOnClickListener {
            val usersCollection: CollectionReference = db.collection("users")

            val docRef: DocumentReference = usersCollection.document(searchEt.text.toString())

            docRef.get().apply {
                addOnSuccessListener {
                    if(!it["login"].toString().equals("null"))
                        resultTV.text = it["login"].toString() + " - " + it["name"].toString() + " - " +
                                (it["lastlogin"] as Timestamp).toDate() +
                                " " + it["logintimes"].toString()
                    else
                        resultTV.text = "User not found"

                    searchEt.text.clear()
                }
            }
        }

        editBtn.setOnClickListener {
            val myIntent = Intent(this, Edit::class.java)
            startActivityForResult(myIntent, 5435)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 5435) {
            Toast.makeText(
                applicationContext, "User has been modified", Toast.LENGTH_SHORT).show()
        }
    }
}
