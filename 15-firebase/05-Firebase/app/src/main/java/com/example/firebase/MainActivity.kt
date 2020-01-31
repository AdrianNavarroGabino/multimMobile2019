package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.Timestamp

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
                    "name" to nameEt.text.toString()
                )

                db.collection("users")
                    .document(idET.text.toString()).set(user)
                    .addOnSuccessListener { _ ->
                        resultTV.text = "User added"
                        idET.text.clear()
                        loginET.text.clear()
                        nameEt.text.clear()
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
    }
}
