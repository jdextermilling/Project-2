package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declarations
    Button myFavoritesButton;
    Button sizeSearchButton;
    Button busynessSearchButton;
    Button cleanlinessSearchButton;
    Button goSearchButton;

// ------------- This is what happens when the app is first opened. -----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the buttons to thier views
        myFavoritesButton = (Button) findViewById(R.id.myFavoritesButton);
        sizeSearchButton = (Button) findViewById(R.id.sizeSearchButton);
        busynessSearchButton = (Button) findViewById(R.id.busynessSearchButton);
        cleanlinessSearchButton = (Button) findViewById(R.id.cleanlinessSearchButton);

        // Creating a singleton of the database for this activity.
        DataBaseHelper db = DataBaseHelper.getInstance(MainActivity.this);
        // Populating the database, the favorites column is set to '0' initially.
        db.insert(1, "Dolores", 16, 10, 5, 0, R.drawable.dolores_park);
        db.insert(2, "Marina Green", 74, 8, 9, 0, R.drawable.marina_green);
        db.insert(3, "Alta Plaza", 12, 6, 10, 0, R.drawable.alta_plaza);
        db.insert(4, "Golden Gate", 1017, 9, 8, 0, R.drawable.golden_gate);
        db.insert(5, "Lands End", 45, 4, 9, 0, R.drawable.lands_end);
        db.insert(6, "Lake Merced", 614, 7, 7, 0, R.drawable.lake_merced);
        db.insert(7, "Bernal Heights", 26, 5, 6, 0, R.drawable.bernal_heights);
        db.insert(8, "Washington Square", 10, 10, 6, 0, R.drawable.washington_square);
        db.insert(9, "Pioneer Park", 5, 7, 9, 0, R.drawable.pioneer_park);



        // Creating the methods for the functionality of the button
        myFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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




    }

//    private int getDrawableValue(String picture){
//        switch(picture){
//            case "Dolores":
//                return android.R.drawable.;
//            case "Marina Green":
//                return android.R.drawable.ic_menu_add;
//            case "Alta Plaza":
//                return android.R.drawable.ic_menu_upload;
//            case "Golden Gate":
//                return android.R.drawable.ic_media_play;
//            case "Lands End":
//                return android.R.drawable.ic_media_play;
//            case "Lake Merced":
//                return android.R.drawable.ic_media_play;
//            case "Bernal Heights":
//                return android.R.drawable.ic_media_play;
//            case "Washington Square":
//                return android.R.drawable.ic_media_play;
//            case "Pioneer Park":
//                return android.R.drawable.ic_media_play;
//            default:
//                return 0;
//        }
//    }
}
