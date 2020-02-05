package com.ekinsogut.gourmet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    DbHelper v1;

    String nameT;
    String surnameT;
    String passwordT;
    EditText nameText;
    EditText surnameText;
    EditText passwordText;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
        passwordText = findViewById(R.id.passwordText);

        button3 = findViewById(R.id.button3);

        v1 = new DbHelper(this);

    }

    private void UserRegister(String Username , String Usersurname , String UserPassword){

        SQLiteDatabase db = v1.getWritableDatabase();
        db.isOpen();
        ContentValues cv1 = new ContentValues();
        cv1.put("Name",Username);
        cv1.put("Surname",Usersurname);
        cv1.put("Password",UserPassword);

        db.insertOrThrow("UsersTable",null,cv1);
    }

    public void button3(View view){

        nameT = nameText.getText().toString();
        surnameT = surnameText.getText().toString();
        passwordT = passwordText.getText().toString();

        if (nameT.length() <= 0 || nameT.length() > 20){

            Toast.makeText(Main3Activity.this,"Between 0-20 Characters for Name!",Toast.LENGTH_LONG).show();

        } else if (surnameT.length() <= 0 || surnameT.length() > 20){

            Toast.makeText(Main3Activity.this,"Between 0-20 Characters for Surname!",Toast.LENGTH_LONG).show();

        } else if (passwordT.length() <= 0 || passwordT.length() > 5){

            Toast.makeText(Main3Activity.this,"Between 0-7 Characters for Password!",Toast.LENGTH_LONG).show();

        }

        else {


            UserRegister(nameT, surnameT, passwordT);
            Toast.makeText(getApplicationContext(), "OK!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Main3Activity.this, Main2Activity.class);

            intent.putExtra("nameTe", nameT);
            intent.putExtra("surnameTe", surnameT);
            intent.putExtra("passwordTe", passwordT);

            startActivity(intent);

            overridePendingTransition(R.anim.anim_ln, R.anim.anim_out);

        }
    }

}
