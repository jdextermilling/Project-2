package com.example.jacobdexter_milling.sfparkexplorer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Add high level description of what the activity (or class) does
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Declarations.
     */
    Button myFavoritesButton;
    Button sizeSearchButton;
    Button busynessSearchButton;
    Button cleanlinessSearchButton;
    ListView listView;
    Cursor cursor;
    CursorAdapter cursorAdapter;

    private static final String PREF_KEY_FIRST_APP_RUN = "prefKeyFirstAppRun";
    private SharedPreferences sharedPref;

    /**
     * ------------- This is what happens when the app is first opened. --------------------------
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Setting the buttons to their views.
         */
        listView = (ListView) findViewById(R.id.searchableListView);
        listView.setVisibility(View.GONE);
        myFavoritesButton = (Button) findViewById(R.id.myFavoritesButton);
        sizeSearchButton = (Button) findViewById(R.id.sizeSearchButton);
        busynessSearchButton = (Button) findViewById(R.id.busynessSearchButton);
        cleanlinessSearchButton = (Button) findViewById(R.id.cleanlinessSearchButton);

        /**
         * This method controls the shared preferences so that the app knows if
         * it has been run before. This way the database is only initialized once and
         * therefore does not duplicate the data if the app is destroyed.
         */
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        if (checkForFirstTimeeRun()) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(PREF_KEY_FIRST_APP_RUN, false);
            editor.apply();
            initializeDB();
        }

        /**
         * This method handles the functionality of the search menu feature.
         */
        handleIntent(getIntent());

        /**
         * ----- Buttons on the MainActivity -----------
         */


        // Creating the methods for the functionality of the buttons


        myFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByFavoritesActivity.class);
                startActivity(intent);
            }
        });

        sizeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchBySizeResultsActivity.class);
                startActivity(intent);
            }
        });

        busynessSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByBusynessResultsActivity.class);
                startActivity(intent);
            }
        });

        cleanlinessSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByCleanlinessActivity.class);
                startActivity(intent);
            }
        });

        myFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByFavoritesActivity.class);
                startActivity(intent);
            }
        });
    } /** --------- End Main Method ---------------------------------------------------------------

     /**
     * ------ Methods for support within the Main Activity ----------------------------------------
     */

    /**
     * Method for the search menu feature.
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /**
     * Second method for the search menu feature.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem searchMenu = menu.findItem(R.id.search);
        /**
         * Associate searchable configuration with the SearchView.
         */
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        ImageView closeButton = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText searchText = (EditText) findViewById(android.support.v7.appcompat.R.id.search_src_text);

                searchText.setText("");

                searchView.setQuery("", false);
                searchView.onActionViewCollapsed();
                searchMenu.collapseActionView();

                listView.setVisibility(View.GONE);

            }
        });

        MenuItemCompat.setOnActionExpandListener(searchMenu, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                listView.setVisibility(View.GONE);
                return true;
            }
        });

        return true;
    }

    /**
     * Third method for search menu feature.
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("Getting the search", query);
            DataBaseHelper.getInstance(MainActivity.this).returnGlobalSearch(query);
            DataBaseHelper helper = DataBaseHelper.getInstance(MainActivity.this);
            cursor = helper.returnGlobalSearch(query);
            cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{DataBaseHelper.COL_NAME}, new int[]{android.R.id.text1}, 0);
            listView.setAdapter(cursorAdapter);
            listView.setVisibility(View.VISIBLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    /**
                     * This takes the list item and sends it to the DetailsActivity.
                     */
                    Intent intentForDetails = new Intent(MainActivity.this, DetailsActivity.class);
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

    /**
     * This method take the arraylist parkItems from the PopulateDBItems class and puts the
     * data into the database so that it can be called upon later.
     */
    private void initializeDB() {
        ArrayList<ParkItem> parkItems = PopulateDBItems.getParkItems(this);
        for (ParkItem item : parkItems) {
            DataBaseHelper.getInstance(this).insert(item.getPrimaryKey(), item.getName(), item.getSize(), item.getBusyness(),
                    item.getCleanliness(), item.getFavorite(), item.getImageResourceID());
        }
    }

    /**
     * Method for checking if the app has been before and setting the shared preference value.
     *
     * @return a boolean indicating if the app has been run before.
     */
    private boolean checkForFirstTimeeRun() {
        boolean isFirstRun = sharedPref.getBoolean(PREF_KEY_FIRST_APP_RUN, true);
        return isFirstRun;
    }

    /**
     * Sets the click listeners for ALL views in the Activity
     *
     * TODO: THIS WILL BE USED FOR REFACTORING
     */
//    private void initClickListeners() {
//        setButtonClickListener(sizeSearchButton, );
//        setButtonClickListener(busynessSearchButton, );
//        setButtonClickListener(cleanlinessSearchButton, );
//        setButtonClickListener(myFavoritesButton, );
//    }
//
//    private void setButtonClickListener(Button button, final String ) {
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getIntentForSearchResult();
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private Intent getIntentForSearchResult(String key, String value, Class classToLaunch) {
//        Intent intentMainToResultsActivity = new Intent(MainActivity.this, classToLaunch);
//        intentMainToResultsActivity.putExtra(key, value);
//
//        return ;
//    }
}
