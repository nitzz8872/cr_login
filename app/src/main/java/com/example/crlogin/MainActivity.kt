package com.example.crlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()


        button.setOnClickListener {
            doLogin()
        }
    }
    private fun doLogin(){
        auth.signInWithEmailAndPassword(eemail.text.toString(), ppassword.text.toString())
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    Toast.makeText(baseContext, "Authentication done.",
                        Toast.LENGTH_SHORT).show()
                    val user=auth.currentUser
                    updateUI(user)


                }
                else{
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }


            }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
            if(currentUser!=null){
                startActivity(Intent(this,Main2Activity::class.java))
            }
    }



}
