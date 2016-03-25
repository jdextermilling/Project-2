package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JacobDexter-Milling on 3/15/16.
 */

/**
 * This class provides the methods for creating and manipulating the database.
 *
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    /**
     * Key for passing data from Results Activity to Details activity via intent.
     */
    public static final String DATA_KEY = "DataKey";

    /**
     * Creating the data base ParkItem.
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PARKS_DB";

    /**
     * Creating the table ParkItem List Table.
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


    public static final String SQL_DROP_PARKS_TABLE = "DROP TABLE IF EXISTS parkItems";

    public static final String[] PARKS_LIST_COLUMNS = {COL_ID, COL_NAME, COL_SIZE, COL_BUSYNESS, COL_CLEANLINESS, COL_FAVORITE, COL_IMAGE};


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Declaring the DataBaseHelper
    private static DataBaseHelper sInstance;

    // Creating the database singleton
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

    /**
     * Method for taking database entries (data) and inserting into the database.
     * @param id is an int for the unique id for the table.
     * @param name is a String for the name of the item.
     * @param size is an int for the size of the item.
     * @param busyness is an int for the busyness of the item.
     * @param cleanliness is an int for the cleanliness for the item.
     * @param favorite is an int representing '1 = is favorite' or '0 = in NOT favorite.
     * @param image is an int that is a reference to the drawable resource file.
     */
    public void insert(int id, String name, int size, int busyness, int cleanliness, int favorite, int image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        // Because AUTOINCEMENT was implemented in Table creation, this insert function is disabled.
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

    /**
     * Method for returning search of all LIKE names to the search menu feature on Main Activity.
     * @param query is taken from the user in the input field.
     * @return a cursor.
     */
    public Cursor returnGlobalSearch(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PARKS_LIST_TABLE_NAME, // a. table
                PARKS_LIST_COLUMNS, // b. column names
                COL_NAME+" LIKE ?", // c. selections
                new String[]{"%"+query+"%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    /**
     * Method for returning search of database for all parkItems but ranked by size.
     * @return a cursor.
     */
    public Cursor returnParksRankedBySize(){
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

    /**
     * Method for returning search of database for all parkItems but ranked by busyness.
     * @return a cursor.
     */
    public Cursor returnParksRankedByBusyness(){
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

    /**
     * Method for returning search of database for all parkItems but ranked by cleanliness.
     * @return a cursor.
     */
    public Cursor returnParksRankedByCleanliness(){
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

    /**
     * Method for returning search of database to show the favorites.
     * @return a cursor.
     */
    public Cursor returnParksThatBeFavorites(){
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

    /**
     * Method for returning a park name based on its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the name from the COLUMN NAME at the specified row.
     */
    public String returnParkName(int id){
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

    /**
     * Method for returning a park image based on its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the image from the COLUMN IMAGE at the specified row.
     */
    public int returnParkImage(int id){
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

    /**
     * Method for returning a park size value based on its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the size from the COLUMN _id at the specified row.
     */
    public int returnParkSizeValue(int id){
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

    /**
     * Method for returning a park busyness value based on its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the busyness value from the COLUMN _id at the specified row.
     */
    public int returnParkBusynessValue(int id){
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

    /**
     * Method for returning a park cleanliness value based on its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the cleanliness value from the COLUMN _id at the specified row.
     */
    public int returnParkCleanlinessValue(int id){
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

    /**
     * Method for returning a park based on if it's a favorite by its primary key _id.
     * @param id is an int identifying the row to be used.
     * @return the favorite status value from the COLUMN _id at the specified row.
     */
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

    /**
     * Method used in Details Activity to update the row in the DB to a favorite.
     * @param id
     * @param favStatus
     */
    public void dbInsert(int id, int favStatus){
        // Making the database accessible.
        SQLiteDatabase db = getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE, favStatus);
        // Which row to update, based on the ID
        String selection = COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.update(
                PARKS_LIST_TABLE_NAME,
                values,
                selection,
                selectionArgs);
        db.close();
    }
}

