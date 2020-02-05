package com.ekinsogut.gourmet;

public class Comment {

    static String getSql(){

        return "CREATE TABLE CommentTable(ID INTEGER PRIMARY KEY AUTOINCREMENT,Comment TEXT,Rest TEXT,RestAd TEXT,Latitude TEXT,Longitude TEXT,Point TEXT, Status BOOLEAN DEFAULT 0,UsersID INTEGER,FOREIGN KEY(UsersID) REFERENCES UsersTable(ID) DEFERRABLE INITIALLY DEFERRED)";
    }
}

