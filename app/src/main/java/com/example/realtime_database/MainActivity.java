package com.example.realtime_database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name, email, password, cPassword, number;
    Button add;

    DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        FirebaseDatabase fb = FirebaseDatabase.getInstance();

        database = fb.getReference().child("users");

        add = findViewById(R.id.add);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.cPassword);
        number = findViewById(R.id.number);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addDataToFirebase();
            }
        });


    }

    public void addDataToFirebase(){

        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String confirmPassword = cPassword.getText().toString().trim();
        String Number = number.getText().toString().trim();


        if(Name.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your name" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(Email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your email" , Toast.LENGTH_SHORT).show();
            return;
        } if(Password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your password" , Toast.LENGTH_SHORT).show();
            return;
        } if(confirmPassword.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Confirm password" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Password.equals(confirmPassword)) {

            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Number.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your name" , Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(Name, Email, Password, Number);
        database.push().setValue(user).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Data added Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Data is not added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

}