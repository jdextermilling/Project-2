package com.example.jacobdexter_milling.listmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    // Delcaring my Views and button
    TextView detailListName;
    EditText detailItemNamer;
    Button detailListItemCreatorButton;
    Button backButton;
    ListView detailListView;
    private  int index;
    Intent intent;

    // Declaring the detail list that will hold items the user adds to it.
    ArrayList<String> detailList;

    // Declaring the ArrayListAdapter
    ArrayAdapter detailListAdapter;

    // The following is what happens when the DetailActivity is started.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent = getIntent();

        // Instantiating View and button
        detailListName = (TextView) findViewById(R.id.detailListName);
        detailItemNamer = (EditText) findViewById(R.id.detailItemNamer);
        detailListItemCreatorButton = (Button) findViewById(R.id.detailListItemCreatorButton);
        backButton = (Button) findViewById(R.id.backButton);
        detailListView = (ListView) findViewById(R.id.detailListView);

        // Call this method to retrieve title or populate a new title for the detail view
        retrieveOrPopulateTitle();

        // Instantiating the detailList
        detailList = getDataList();

        // Call this method to retrieve the data from from an exsisting list.
        settingDetailAdapter();



        // ----- Buttons ---------------------------

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonPressed();
            }
        });

        detailListItemCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = detailItemNamer.getText().toString();
                detailList.add(itemName);
                detailListAdapter.notifyDataSetChanged();
                detailItemNamer.getText().clear();


            }
        });

    } // --------- End OnCreate block ------------------------------------------------------------------

    // ---------- Start methods for operating DetailActivity--------------------------------------------------------------------


    private void retrieveOrPopulateTitle() {
        if (!intent.toString().isEmpty()) {
            detailListName.setText(getListTitle() + " List");
        }
    }

    private ArrayList<String> getDataList() {
        Intent returnIntent = getIntent();
        if (returnIntent == null) {
            return new ArrayList<>();
        }
        return returnIntent.getStringArrayListExtra(MainActivity.DATA_KEY);
    }

    // Method for setting up the DetailAdapter for controlling the data in the detailView
    private void settingDetailAdapter() {
        detailListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, detailList);
        //Setting the adapter to the listView in this activity
        detailListView.setAdapter(detailListAdapter);
        detailListAdapter.notifyDataSetChanged();
    }

    // This method retrieves the title of the list created in the MainActivity
    private String getListTitle() {
        Intent returnIntent = getIntent();
        if (returnIntent == null) {
            return "";
        }
        return returnIntent.getStringExtra(MainActivity.DATA_LIST_TITLE);

    }

    // This method retrieves the index, and so the contents of the list, coming from the MainActivity.
    private int getDataIndex() {
        Intent returnIntent = getIntent();
        if (returnIntent == null) {
            return MainActivity.ERROR_INDEX;
        }
        return returnIntent.getIntExtra(MainActivity.DATA_INDEX_KEY, MainActivity.ERROR_INDEX);
    }


    // -------- Start methods for sending data back from DetailsActivity to MainActivity-----------------

    // This method creates a new intent to send data back to the MainActivity.
    private void sendNewListBack() {
        // Create the new intent
        Intent returnIntent = getIntent();
        if (returnIntent == null) {
            return;
        }
        returnIntent.putExtra(MainActivity.DATA_KEY, detailList);
        returnIntent.putExtra(MainActivity.DATA_INDEX_KEY, index);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    // This method provides a call for the sendNewListBack when the backButton is presses.
    public void onBackButtonPressed() {
        sendNewListBack();
    }

}
