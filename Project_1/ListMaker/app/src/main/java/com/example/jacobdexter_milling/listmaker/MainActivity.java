package com.example.jacobdexter_milling.listmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Things I'm not using yet:
    int index;


    // Declarations
    //EditText for naming a newly created list.
    EditText listNamer;
    Button listCreatorButton;
    ListView collectionOfListsListView;
    // TODO: The collectionOfLists should not be needed. remove once functionality with mainList is established.
    ArrayList<ArrayList<String>> collectionOfLists;

    // The mainList is the list of lists.
    ArrayList<String> mainList;

    // the mainListAdapter takes the data provided from the mainList array and sets it
    // to the NEED: is this adapater needed??
    ArrayAdapter mainListAdapter;


    // These variables are used to send data in the inent to the DetailActivity
    // and retrieve updated lists from that activity.
    private static final int MAIN_REQUEST_CODE = 7;
    public static final String DATA_KEY = "myDataKey";
    public static final String DATA_INDEX_KEY = "myDataIndexKey";
    public static final int ERROR_INDEX = -2;
    public static final String DATA_LIST_TITLE = "newListTitle";

    // All the following happens when you start the app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating Views and button
        listNamer = (EditText) findViewById(R.id.listNamer);
        listCreatorButton = (Button) findViewById(R.id.listCreatorButton);
        collectionOfListsListView = (ListView) findViewById(R.id.collectionOfListsListView);

        // Instantiating the mainList and collectionOfLists
        mainList = new ArrayList();
        collectionOfLists = new ArrayList<>();

        // Adding the mainList to the collectionOfLists
        ArrayList<String> tempList = new ArrayList<>();
        collectionOfLists.add(tempList);


        mainListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainList);
        // Setting the ArrayAdapter to the listOfLists listView.
        collectionOfListsListView.setAdapter(mainListAdapter);
        mainListAdapter.notifyDataSetChanged();

        // This button when clicked will create a new item in mainList and collectionOfLists
        listCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInputsLists = listNamer.getText().toString();
                //Checks if the editText is empty and doesn't let the user enter an empty list name
                if (!userInputsLists.isEmpty()) {
                    mainList.add(userInputsLists);
                    ArrayList<String> placeHolderList = new ArrayList<>();
                    collectionOfLists.add(placeHolderList);
                    mainListAdapter.notifyDataSetChanged();
                    listNamer.getText().clear();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name for a list", Toast.LENGTH_SHORT);
                }

            }
        });

        collectionOfListsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // This takes the list item and sends it to the DetailActivty
                Intent intentForDetail = new Intent(MainActivity.this, DetailActivity.class);
                intentForDetail.putExtra(DATA_KEY, collectionOfLists.get(position));
                // start activity and expect a result back for THIS REQUEST CODE
                // this makes is so when then list is editd in the DetailActivity the collectionOfLists will be
                // updated when the user returns to the MainActivity.
                intentForDetail.putExtra(DATA_LIST_TITLE, mainList.get(position));
                startActivityForResult(intentForDetail, MAIN_REQUEST_CODE);
            }
        });

    }

    // This method retrieves the edited list from the DetailActivity and updates the data in the mainList
    // which lives in the collectionOfLists
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MAIN_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                if (data != null) {
                    ArrayList<String> tempItemList = data.getStringArrayListExtra(DATA_KEY);
                    int index = data.getIntExtra(DATA_INDEX_KEY, ERROR_INDEX);
                    if (index != ERROR_INDEX){
                        collectionOfLists.set(index, tempItemList);
                    }
                    printData(collectionOfLists.get(index));
                }
            } else  if (requestCode == RESULT_CANCELED){
            }
        }
    }

    // This method assists the onActivityResult method
    private void printData(ArrayList<String> data){
        if (data == null){
            return;
        }
        for (String item : data){
            Log.d("Main", item);
        }
    }
}
