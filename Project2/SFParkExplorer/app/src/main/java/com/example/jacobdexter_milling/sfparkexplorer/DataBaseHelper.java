package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JacobDexter-Milling on 3/15/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATA_KEY = "DataKey";

    /*
    Creating the data base Parks
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PARKS_DB";
    /*
    Creating the table Parks List
     */
    public static final String PARKS_LIST_TABLE_NAME = "PARKS_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "NAME";
    public static final String COL_SIZE = "SIZE";
    public static final String COL_BUSYNESS = "BUSYNESS";
    public static final String COL_CLEANLINESS = "CLEANLINESS";
    public static final String COL_FAVORITE = "FAVORITE";
    public static final String COL_IMAGE = "IMAGE";

    public static final String SQL_CREATE_PARKS_TABLE = "CREATE TABLE "
            + PARKS_LIST_TABLE_NAME
            + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_SIZE + " INTEGER, "
            + COL_BUSYNESS + " INTEGER, "
            + COL_CLEANLINESS + " INTEGER, "
            + COL_FAVORITE + " INTEGER, "
            + COL_IMAGE + " INTEGER )";

    public static final String SQL_DROP_PARKS_TABLE = "DROP TABLE IF EXISTS parks";

    public static final String[] PARKS_LIST_COLUMNS = {COL_ID, COL_NAME, COL_SIZE, COL_BUSYNESS, COL_CLEANLINESS, COL_FAVORITE, COL_IMAGE};


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DataBaseHelper sInstance;

    public static DataBaseHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PARKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_PARKS_TABLE);
        onCreate(db);
    }

    public void insert(int id, String name, int size, int busyness, int cleanliness, int favorite, int image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COL_ID, id);
        values.put(COL_NAME, name);
        values.put(COL_SIZE, size);
        values.put(COL_BUSYNESS, busyness);
        values.put(COL_CLEANLINESS, cleanliness);
        values.put(COL_FAVORITE, favorite);
        values.put(COL_IMAGE, image);
        db.insert(PARKS_LIST_TABLE_NAME, null, values);
        db.close();
    }


    public Cursor getParksRankedBySize(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                PARKS_LIST_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COL_SIZE, // g. order by
                null); // h. limit

        return cursor;
    }

    public Cursor getParksRankedByBusyness(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                PARKS_LIST_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COL_BUSYNESS, // g. order by
                null); // h. limit

        return cursor;
    }

    public Cursor getParksRankedByCleanliness(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                PARKS_LIST_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COL_CLEANLINESS, // g. order by
                null); // h. limit

        return cursor;
    }

    public Cursor getParksThatBeFavorites(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                PARKS_LIST_COLUMNS, // b. column names
                COL_FAVORITE, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

    public String getParkName(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_NAME}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        } else {
            return "";
        }
    }

    public int getParkImage(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_IMAGE}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_IMAGE));
        } else {
            return 1;
        }
    }

    public int getParkSizeValue(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_SIZE}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_SIZE));
        } else {
            return 1;
        }
    }

    public int getParkBusynessValue(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_BUSYNESS}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_BUSYNESS));
        } else {
            return 1;
        }
    }

    public int getParkCleanlinessValue(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_CLEANLINESS}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_CLEANLINESS));
        } else {
            return 1;
        }
    }

    public int getParkFavorite(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                new String[] {COL_FAVORITE}, // b. column names
                COL_ID+" = ?", // c. selections
                new String[]{Integer.toString(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_FAVORITE));
        } else {
            return 1;
        }
    }
}

