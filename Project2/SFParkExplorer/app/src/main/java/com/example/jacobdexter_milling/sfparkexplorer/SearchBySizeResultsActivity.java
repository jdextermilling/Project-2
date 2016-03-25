package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * This activity defines the functionality for of the page a user sees when they search by
 * the cleanliness category from the Main Activity.
 */

public class SearchBySizeResultsActivity extends AppCompatActivity {


    /**
     * Declarations
     */
    ListView resultsListView;
    CursorAdapter cursorAdapter;

    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_for_size);

        DataBaseHelper helper = DataBaseHelper.getInstance(SearchBySizeResultsActivity.this);
        cursor = helper.returnParksRankedBySize();
        resultsListView = (ListView)findViewById(R.id.resultsListView);
        cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{DataBaseHelper.COL_NAME},new int[]{android.R.id.text1},0);
        resultsListView.setAdapter(cursorAdapter);

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * This takes the list item and sends it to the DetailsActivty.
                 */
                Intent intentForDetails = new Intent(SearchBySizeResultsActivity.this, DetailsActivity.class);
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
