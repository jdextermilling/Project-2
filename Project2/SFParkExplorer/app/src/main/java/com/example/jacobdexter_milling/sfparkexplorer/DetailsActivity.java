package com.example.jacobdexter_milling.sfparkexplorer;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    // Declarations
    Button addToFavoritesButton;
    Button removeFromFavoritesButton;
    ImageView imageView;
    TextView nameTextView;
    TextView sizeTextView;
    TextView busynessTexView;
    TextView cleanlinessTextView;
    TextView favoritesStatusTextView;

    Intent intent;

    int id;
    int image;
    String parkName;
    int parkSize;
    int parkBusyness;
    int parkCleanliness;
    int parkFavorite;

    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Instantiations
        addToFavoritesButton = (Button) findViewById(R.id.addToFavoritesButton);
        removeFromFavoritesButton = (Button) findViewById(R.id.removeFromFavoritesButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        sizeTextView = (TextView) findViewById(R.id.sizeTextView);
        busynessTexView = (TextView) findViewById(R.id.busynessTextView);
        cleanlinessTextView = (TextView) findViewById(R.id.cleanlinessTextView);
        favoritesStatusTextView = (TextView) findViewById(R.id.favoritesStatusTextView);


        // Creating the helper variable to connect this class to the database singleton
        helper = DataBaseHelper.getInstance(this);

        // id is a method to retrieve the _id from the intent coming fom the results activity
        id = retrieveIntentForPark_id();

        // These us _id to query the DB for their respective variables
        image = helper.getParkImage(id);
        parkName = helper.getParkName(id);
        parkSize = helper.getParkSizeValue(id);
        parkBusyness = helper.getParkBusynessValue(id);
        parkCleanliness = helper.getParkCleanlinessValue(id);
        parkFavorite = helper.getParkFavorite(id);

        // These take the results of the quries above and place the values into the views
        imageView.setBackgroundResource(image);
        nameTextView.setText(parkName);
        sizeTextView.setText(String.valueOf(parkSize + " acres in size."));
        busynessTexView.setText(String.valueOf(parkBusyness + " out of 10 for Busyness."));
        cleanlinessTextView.setText(String.valueOf(parkCleanliness + " out of 10 for Cleanliness."));
        favoritesStatusTextView.setText(String.valueOf(parkFavorite));







        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: code for adding park to favorites
            }
        });

        removeFromFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: code to remove park from favorites
            }
        });


    }

    // Method for retrieving the item _id from results activity
    public int retrieveIntentForPark_id() {
        Intent sentIntent = getIntent();
        return sentIntent.getIntExtra(DataBaseHelper.DATA_KEY, -1);
    }









}

