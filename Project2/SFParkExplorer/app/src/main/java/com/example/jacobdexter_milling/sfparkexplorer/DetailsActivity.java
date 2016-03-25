package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity defines the functionality of the Details page. The activity take the intent from the
 * previous results or favorite activity and populates based on the park being selected.
 *
 */

public class DetailsActivity extends AppCompatActivity {

    /**
     * Declarations
     */
    Button addToFavoritesButton;
    Button removeFromFavoritesButton;
    ImageView imageView;
    TextView nameTextView;
    TextView sizeTextView;
    TextView busynessTexView;
    TextView cleanlinessTextView;

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

        /**
         * Instantiations.
         */
        addToFavoritesButton = (Button) findViewById(R.id.addToFavoritesButton);
        removeFromFavoritesButton = (Button) findViewById(R.id.removeFromFavoritesButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        sizeTextView = (TextView) findViewById(R.id.sizeTextView);
        busynessTexView = (TextView) findViewById(R.id.busynessTextView);
        cleanlinessTextView = (TextView) findViewById(R.id.cleanlinessTextView);


        /**
         * Creating the helper variable to connect this class to the database singleton.
         */
        helper = DataBaseHelper.getInstance(this);

        /**
         * id is a method to retrieve the _id from the intent coming fom the results activity.
         */
        id = retrieveIntentForPark_id();

        /**
         * These use _id to query the DB for their respective variables.
         */
        image = helper.returnParkImage(id);
        parkName = helper.returnParkName(id);
        parkSize = helper.returnParkSizeValue(id);
        parkBusyness = helper.returnParkBusynessValue(id);
        parkCleanliness = helper.returnParkCleanlinessValue(id);
        parkFavorite = helper.getParkFavorite(id);

        /**
         * These take the results of the queries above and place the values into the views.
         */
        imageView.setBackgroundResource(image);
        nameTextView.setText(parkName);
        sizeTextView.setText(String.valueOf(parkSize + " acres in size."));
        busynessTexView.setText(String.valueOf(parkBusyness + " out of 10 for Busyness."));
        cleanlinessTextView.setText(String.valueOf(parkCleanliness + " out of 10 for Cleanliness."));


        /**
         * This button runs a method from DataBaseHelper to make a given park a favorite.
         */
        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.dbInsert(id,  1);
                Toast.makeText(DetailsActivity.this, "ParkItem Added to My Favorites", Toast.LENGTH_SHORT).show();

            }
        });

        /**
         * This button runs a method from DatabaseHelper to remove the given park from favorites.
         */
        removeFromFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.dbInsert(id, 0);
                Toast.makeText(DetailsActivity.this, "ParkItem Removed from My Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method for retrieving the item _id from results activity.
     * @return
     */
    public int retrieveIntentForPark_id() {
        Intent sentIntent = getIntent();
        return sentIntent.getIntExtra(DataBaseHelper.DATA_KEY, -1);
    }
}

