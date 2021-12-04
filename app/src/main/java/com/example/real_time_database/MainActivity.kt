package com.example.real_time_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var namet:EditText
    lateinit var carnamet:EditText
    lateinit var ratinngstarn: RatingBar
    lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        namet = findViewById(R.id.edit_name_text)
        carnamet = findViewById(R.id.car_name_edtext)
        ratinngstarn = findViewById(R.id.ratingBar_1)
        submitBtn = findViewById(R.id.button_1)

        submitBtn.setOnClickListener{
            Savecar()
        }
    }
    private fun Savecar(){
        val name = namet.text.toString().trim()
        val carname = carnamet.text.toString().trim()

        if(name.isEmpty()){
            namet.error = "please enter your name"
            return
        }
        if(carname.isEmpty()){
            carnamet.error = "please enter the car name"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("cars")
        val carId = ref.push().key
        val car = carData(name,carname,ratinngstarn.numStars)

        if (carId != null) {
            ref.child(carId).setValue(car).addOnCompleteListener{
                Toast.makeText(this,"Car and its rating is saved successfully", Toast.LENGTH_LONG).show()
            }
        }
    }
}