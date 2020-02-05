package com.ekinsogut.gourmet;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    DbHelper v1;
    TextView textView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_place, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_place) {

            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

            startActivity(intent);

            overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

        }

        if (item.getItemId() == R.id.share_button){

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody = "GOURMENT APP";

            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);

            startActivity(Intent.createChooser(shareIntent,"Share Us!"));

        }

        if (item.getItemId() == R.id.change_user){

            Intent changeIntent = new Intent(getApplicationContext(), MainActivity.class);

            startActivity(changeIntent);

            overridePendingTransition(R.anim.anim_ln,R.anim.anim_out);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        v1 = new DbHelper(this);

        int syc = 0;

        textView = findViewById(R.id.textView);

        final Intent intent = getIntent();

        final String userID = intent.getStringExtra("userid");

        textView.setText(userID);


        ListView listView = (ListView) findViewById(R.id.listview);


        SQLiteDatabase db = v1.getReadableDatabase();

        String[] information = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        final String[] index = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};


        Cursor cursor = db.rawQuery("Select * From CommentTable where Usersid = " + userID, null);

        db.isOpen();

        while (cursor.moveToNext()) {

            information[syc] = " Rest :" + cursor.getString(cursor.getColumnIndex("Rest"))
                    + "\n Comment: " + cursor.getString(cursor.getColumnIndex("Comment"))
                    + "\n Address: " + cursor.getString(cursor.getColumnIndex("RestAd"))
                    + "\n Latitude: " + cursor.getString(cursor.getColumnIndex("Latitude"))
                    + "\n Longitude: " + cursor.getString(cursor.getColumnIndex("Longitude"))
                    + "\n Point: " + cursor.getString(cursor.getColumnIndex("Point"));

            //Color.BLACK(index[syc]);

            index[syc] = cursor.getString(cursor.getColumnIndex("ID"));

            syc++;
        }

        ArrayAdapter<String> dataAdaptorComment = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1,information);

        listView.setAdapter(dataAdaptorComment);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertMessage = new AlertDialog.Builder(Main4Activity.this);

                alertMessage.setTitle("Delete");
                alertMessage.setMessage("Really?");

                alertMessage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CommentDelete(index[position]);
                        Intent intent2 = new Intent();
                        intent2.setClass(Main4Activity.this,Main4Activity.class);
                        intent2.putExtra("userid",textView.getText());
                        startActivity(intent2);
                    }
                });

                alertMessage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Main4Activity.this,"Cancel!",Toast.LENGTH_SHORT).show();
                    }
                });



                alertMessage.show();

            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertMessagetwo = new AlertDialog.Builder(Main4Activity.this);

                alertMessagetwo.setTitle("Location");
                alertMessagetwo.setMessage("Really?");

                alertMessagetwo.setPositiveButton("Go!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);

                        SQLiteDatabase db = v1.getReadableDatabase();

                        String Lat = null;
                        String Lang = null;

                        Cursor cursor1 = db.rawQuery("Select * from CommentTable where ID =" + index[position],null);
                        db.isOpen();

                        while (cursor1.moveToNext()){

                           Lat = cursor1.getString(cursor1.getColumnIndex("Latitude"));
                           Lang = cursor1.getString(cursor1.getColumnIndex("Longitude"));

                        }

                        intent2.putExtra("Lat",Lat);
                        intent2.putExtra("Lang",Lang);

                        startActivity(intent2);

                    }
                });

                alertMessagetwo.setNegativeButton("Share", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        SQLiteDatabase db = v1.getReadableDatabase();

                        String Latlink = null;
                        String Langlink = null;

                        Cursor cursor1 = db.rawQuery("Select * from CommentTable where ID =" + index[position],null);

                        db.isOpen();

                        while (cursor1.moveToNext()){

                            Latlink = cursor1.getString(cursor1.getColumnIndex("Latitude"));
                            Langlink = cursor1.getString(cursor1.getColumnIndex("Longitude"));

                        }

                        Intent shareIntent2 = new Intent(Intent.ACTION_SEND);
                        shareIntent2.setType("text/plain");
                        String shareBody = "https://www.google.com.tr/maps/dir/"+Latlink+","+Langlink+"/"+Latlink+","+Langlink+"/@"+Latlink+","+Langlink+",16z?hl=tr";

                        shareIntent2.putExtra(Intent.EXTRA_TEXT,shareBody);

                        startActivity(Intent.createChooser(shareIntent2,"Share Us!"));

                    }
                });

                alertMessagetwo.show();

                return true;
            }

        });
    }

    private void CommentDelete (String CommentID){

        SQLiteDatabase db = v1.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        db.delete("CommentTable", "ID = " + CommentID, null);
        Toast.makeText(Main4Activity.this, "Comment deleted!", Toast.LENGTH_LONG).show();

    }

}
