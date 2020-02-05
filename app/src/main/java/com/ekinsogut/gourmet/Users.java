package com.ekinsogut.gourmet;

public class Users {

        static String getSql(){

            return "CREATE TABLE UsersTable(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Surname TEXT,Password TEXT,Status BOOLEAN DEFAULT 0)";

    }
}

