package com.ekinsogut.gourmet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main5Activity extends AppCompatActivity {

    DbHelper v1;

    EditText editText;
    EditText editText3;
    TextView textView2;
    TextView textView3;
    EditText editText2;
    TextView textView8;
    TextView textView9;

    String commentT;
    String restT;
    String addressT;
    String locationoneT;
    String locationtwoT;
    String pointT;
    String userIDT;
    String userpass;
    String endpoint;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflaterback = getMenuInflater();
        menuInflaterback.inflate(R.menu.back_page, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.change_maps) {

            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

            startActivity(intent);

            overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        editText = findViewById(R.id.editText);
        textView2 = findViewById(R.id.textView2);
        editText3 = findViewById(R.id.editText3);
        textView3 = findViewById(R.id.textView3);
        editText2 = findViewById(R.id.editText2);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);

        Intent intent = getIntent();

        //Maps Activity
        String Add = intent.getStringExtra("Add");
        String l1 = intent.getStringExtra("Lat");
        String l2 = intent.getStringExtra("Long");

        textView3.setText(Add);

        textView2.setText(l1);

        textView9.setText(l2);

        v1 = new DbHelper(this);

        UserID iduser = (UserID) getApplicationContext();

        userpass = iduser.getId();

        textView8.setText(userpass);

    }

    private void CommentRegister(String UserComment, String UserRest, String UserAddress, String UserLat,String UserLang, String UserPoint, String UserID){

        SQLiteDatabase db = v1.getWritableDatabase();
        db.isOpen();
        ContentValues cv1 = new ContentValues();
        cv1.put("Comment", UserComment);
        cv1.put("Rest",UserRest);
        cv1.put("RestAd", UserAddress);
        cv1.put("Latitude",UserLat);
        cv1.put("Longitude",UserLang);
        cv1.put("Point",UserPoint);
        cv1.put("UsersID",UserID);

        db.insertOrThrow("CommentTable",null,cv1);
    }


    public void button5(View view){

        commentT = editText3.getText().toString();
        restT = editText.getText().toString();
        addressT = textView3.getText().toString();
        locationoneT = textView2.getText().toString();
        locationtwoT = textView9.getText().toString();
        pointT = editText2.getText().toString();
        userIDT = userpass;

        Integer forpoint = Integer.valueOf(pointT);

        if (forpoint >= 1 && forpoint <= 10){

            endpoint = String.valueOf(forpoint);

            CommentRegister(commentT, restT, addressT, locationoneT,locationtwoT, endpoint, userIDT);
            Toast.makeText(getApplicationContext(), "OK!", Toast.LENGTH_LONG).show();

            Intent intent2 = new Intent();
            intent2.setClass(Main5Activity.this, Main4Activity.class);
            intent2.putExtra("userid", userpass.toString());
            startActivity(intent2);

        } else {

            Toast.makeText(getApplicationContext(),"Plaase 1 between 10!",Toast.LENGTH_LONG).show();
        }
    }
}
