package com.example.jacobdexter_milling.sfparkexplorer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by JacobDexter-Milling on 3/21/16.
 */
public class SearchByFavoritesActivity extends AppCompatActivity {

    ListView resultsListView;
    CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_for_favorites);


        resultsListView = (ListView) findViewById(R.id.resultsListView);

        DataBaseHelper helper = DataBaseHelper.getInstance(SearchByFavoritesActivity.this);
        Cursor cursor = helper.getParksThatBeFavorites();

        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{DataBaseHelper.COL_NAME}, new int[]{android.R.id.text1}, 0);
        resultsListView.setAdapter(cursorAdapter);

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: remove list item from favorite ( change from 1 back to 0)
            }
        });





    }
}
