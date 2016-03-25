package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by JacobDexter-Milling on 3/21/16.
 */

/**
 * This activity defines the functionality for the Favorites page. It populates a list based on what items have been added by
 * the use in the Details Activity.
 *
 */

public class SearchByFavoritesActivity extends AppCompatActivity {

    // Declarations
    ListView resultsListView;
    CursorAdapter cursorAdapter;
    Cursor cursor;
    int id;
    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_for_favorites);


        resultsListView = (ListView) findViewById(R.id.resultsListView);

        /**
         * Creating the helper variable to connect this class to the database singleton.
         */
        helper = DataBaseHelper.getInstance(this);


        /**
         * id is a method to retrieve the _id from the intent coming fom the results activity.
         */
        id = retrieveIntentForPark_id();


        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * This takes the list item and sends it to the DetailsActivity.
                 */
                Intent intentForDetails = new Intent(SearchByFavoritesActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intentForDetails.putExtra(DataBaseHelper.DATA_KEY, cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID)));
                // updated when the user returns to the MainActivity.
                startActivity(intentForDetails);

            }
        });

        resultsListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                helper.dbInsert(id, 0);
                Toast.makeText(SearchByFavoritesActivity.this, "ParkItem Removed from My Favorites", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    /**
     * / Method for retrieving the item _id from results activity
     * @return
     */
    public int retrieveIntentForPark_id() {
        Intent sentIntent = getIntent();
        return sentIntent.getIntExtra(DataBaseHelper.DATA_KEY, -1);
    }

    /**
     * This method pulls from the database to create to the current list of favorites.
     */
    @Override
    protected void onResume() {
        super.onResume();

        cursor = helper.returnParksThatBeFavorites();

        if (cursorAdapter == null) {
            cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                    new String[]{DataBaseHelper.COL_NAME}, new int[]{android.R.id.text1}, 0);
            resultsListView.setAdapter(cursorAdapter);
        } else {
            cursorAdapter.swapCursor(cursor);
            cursorAdapter.notifyDataSetChanged();
        }
    }
}
