package com.example.session20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var myRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = Firebase.database
        myRef = database.getReference("message")
        myRef.addChildEventListener(object : ChildEventListener
        {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?)
            {
                Log.d("ON", "Add ID: " + dataSnapshot.key)
                Log.d("ON", "Add val: " + dataSnapshot.value)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?)
            {
                return
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot)
            {
                return
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?)
            {
                return
            }

            override fun onCancelled(databaseError: DatabaseError)
            {
                Log.w("ON", "Cancel", databaseError.toException())
                Toast.makeText(this@MainActivity, "Didn't Load Comments.", Toast.LENGTH_SHORT).show()
            }
        })

        val myButton = findViewById<Button>(R.id.button)
        myButton.setOnClickListener{
            role()
        }
    }
    private fun role()
    {
        val dice = mutableListOf<Int>()

        for (i in 0 until 4){
            dice.add((1..6).random())
        }
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val userName=findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        myRef.child(timeStamp).setValue(userName)

    }

}