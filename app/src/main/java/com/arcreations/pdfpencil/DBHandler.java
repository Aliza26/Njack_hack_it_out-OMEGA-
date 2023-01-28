package com.arcreations.pdfpencil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

//this java class to create database set database version

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "detailsdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "details";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our Subject column
    private static final String SUB_COL = "sub";

    // below variable id for our Message provided by clg column.
    private static final String MESSAGE_COL = "message";

    // below variable for our Signature column.
    private static final String SIGNATURE_COL = "signature";





    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SUB_COL + " TEXT,"
                + MESSAGE_COL + " TEXT,"
                + SIGNATURE_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }



    // this method is use to add new course to our sqlite database.
    public  void adNewDetails(String sub , String message, String signature){
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(SUB_COL, sub);
        values.put(MESSAGE_COL, message);
        values.put(SIGNATURE_COL, signature);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }








    // we have created a new method for reading all the courses.
    public ArrayList<RegistrationModal> readRegistration() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor registrationCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<RegistrationModal> registrationModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (registrationCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                registrationModalArrayList.add(new RegistrationModal(registrationCourses.getString(1),
                        registrationCourses.getString(2),
                        registrationCourses.getString(3)));
            } while (registrationCourses.moveToNext());
            // moving our cursor to next.

        }
        // at last closing our cursor
        // and returning our array list.
        registrationCourses.close();
        return registrationModalArrayList;
    }

    /* below is the method for deleting our course.*/
    public void deleteRegistration(String sub) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "sub=?", new String[]{sub});
        db.close();
    }


    /* below is the method for updating our courses
    public void updateCourse(String originalName, String courseName, String courseDetail,
                             String clgSession, String clgName) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(COURSE_COL, courseDetail);
        values.put(SESSION_COL, clgSession);
        values.put(CLG_COL, clgName);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalName});
        db.close();
    }*/








    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
