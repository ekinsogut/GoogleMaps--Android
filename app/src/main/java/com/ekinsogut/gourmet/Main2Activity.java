package com.ekinsogut.gourmet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private DbHelper v1;

    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        v1 = new DbHelper(this);


        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);

        Intent intent = getIntent();

        String Nameget = intent.getStringExtra("nameTe");
        String Surnameget = intent.getStringExtra("surnameTe");
        String Passwordget = intent.getStringExtra("passwordTe");

        textView4.setText(Nameget);
        textView5.setText(Surnameget);
        textView6.setText(Passwordget);

        String id = null;

        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From UsersTable where Name ='" + Nameget + "' and Surname = '" + Surnameget + "' and Password = '" + Passwordget + "' and Status = 0",null);

        db.isOpen();

        while (cursor.moveToNext()){

            id = cursor.getString(cursor.getColumnIndex("ID"));

        }
        textView7.setText(id);
    }

    public void button4(View view){


        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

    }
}
