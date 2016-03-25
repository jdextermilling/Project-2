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

/**
 * Created by JacobDexter-Milling on 3/18/16.
 */

/**
 * This activity defines the functionality for of the page a user sees when they search by the busyness category
 * from the Main Activity.
 *
 */

public class SearchByBusynessResultsActivity extends AppCompatActivity {

    /**
     * Declarations
     */
    ListView resultsListView;
    CursorAdapter cursorAdapter;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_for_busyness);

        resultsListView = (ListView) findViewById(R.id.resultsListView);
        DataBaseHelper helper = DataBaseHelper.getInstance(SearchByBusynessResultsActivity.this);
        cursor = helper.returnParksRankedByBusyness();
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{DataBaseHelper.COL_NAME}, new int[]{android.R.id.text1}, 0);
        resultsListView.setAdapter(cursorAdapter);

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * This takes the list item and sends it to the DetailsActivity.
                 */
                Intent intentForDetails = new Intent(SearchByBusynessResultsActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intentForDetails.putExtra(DataBaseHelper.DATA_KEY, cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID)));
                /**
                 * updated when the user returns to the MainActivity.
                 */
                startActivity(intentForDetails);

            }
        });

    }
}
