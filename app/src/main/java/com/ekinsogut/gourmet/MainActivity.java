package com.ekinsogut.gourmet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    DbHelper v1;
    EditText editText;
    EditText editText2;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.nameText);
        editText2 = findViewById(R.id.surnameText);
        button = findViewById(R.id.button3);
        button2 = findViewById(R.id.button3);

    }

    public void button(View view){

        v1 = new DbHelper(this);

        String Password = editText2.getText().toString();
        String id = editText.getText().toString();

        UserID iduser = (UserID) getApplicationContext();
        iduser.setId(id);


        if (editText.getText().toString() == "" || editText2.getText().toString() == ""){
            Toast.makeText(getApplicationContext(),"Must!",Toast.LENGTH_LONG).show();
        } else {

            String name = null;

            SQLiteDatabase db = v1.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * From UsersTable where Status = 0 and Password = '" + Password + "' and ID = '"+ id +"'",null);
            db.isOpen();

            while (cursor.moveToNext()){

                name = cursor.getString(cursor.getColumnIndex("Name"));

            }

            if (name == null){
                Toast.makeText(getApplicationContext(),"False Id or Password!",Toast.LENGTH_LONG).show();
            } else if (name != null){

                Intent intent = new Intent(getApplicationContext(),Main4Activity.class);

                intent.putExtra("userid",id);

                startActivity(intent);

                overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

            }
        }
    }

    public void button2(View view){

        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

    }

}
