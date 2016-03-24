package com.example.jacobdexter_milling.sfparkexplorer;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by JacobDexter-Milling on 3/24/16.
 */
public class PopulateDBItems {

    static ArrayList<ParkItem> parkItems = new ArrayList<>();

    public static ArrayList<ParkItem> getParkItems(Context context){
        ParkItem dolores = new ParkItem();
        dolores.setPrimaryKey(1);
        dolores.setName("Dolores");
        dolores.setSize(16);
        dolores.setBusyness(10);
        dolores.setCleanliness(5);
        dolores.setFavorite(0);
        dolores.setImageResourceID(R.drawable.dolores_park);
        parkItems.add(dolores);

        ParkItem marinaGreen = new ParkItem();
        marinaGreen.setPrimaryKey(2);
        marinaGreen.setName("Marina Green");
        marinaGreen.setSize(74);
        marinaGreen.setBusyness(8);
        marinaGreen.setCleanliness(9);
        marinaGreen.setFavorite(0);
        marinaGreen.setImageResourceID(R.drawable.marina_green_400x533_use);
        parkItems.add(marinaGreen);

        ParkItem altaPlaza = new ParkItem();
        altaPlaza.setPrimaryKey(3);
        altaPlaza.setName("Marina Green");
        altaPlaza.setSize(74);
        altaPlaza.setBusyness(8);
        altaPlaza.setCleanliness(9);
        altaPlaza.setFavorite(0);
        altaPlaza.setImageResourceID(R.drawable.alta_plaza_683x455);
        parkItems.add(altaPlaza);

        ParkItem goldenGate = new ParkItem();
        goldenGate.setPrimaryKey(4);
        goldenGate.setName("Golden Gate");
        goldenGate.setSize(1017);
        goldenGate.setBusyness(9);
        goldenGate.setCleanliness(8);
        goldenGate.setFavorite(0);
        goldenGate.setImageResourceID(R.drawable.golden_gate_re_size);
        parkItems.add(goldenGate);

        ParkItem landsEnd = new ParkItem();
        landsEnd.setPrimaryKey(5);
        landsEnd.setName("Lands End");
        landsEnd.setSize(45);
        landsEnd.setBusyness(4);
        landsEnd.setCleanliness(9);
        landsEnd.setFavorite(0);
        landsEnd.setImageResourceID(R.drawable.lands_end);
        parkItems.add(landsEnd);

        ParkItem lakeMerced = new ParkItem();
        lakeMerced.setPrimaryKey(6);
        lakeMerced.setName("Lake Merced");
        lakeMerced.setSize(614);
        lakeMerced.setBusyness(7);
        lakeMerced.setCleanliness(7);
        lakeMerced.setFavorite(0);
        lakeMerced.setImageResourceID(R.drawable.lake_merced_586x286);
        parkItems.add(lakeMerced);

        ParkItem bernalHeights = new ParkItem();
        bernalHeights.setPrimaryKey(7);
        bernalHeights.setName("Bernal Heights");
        bernalHeights.setSize(26);
        bernalHeights.setBusyness(5);
        bernalHeights.setCleanliness(6);
        bernalHeights.setFavorite(0);
        bernalHeights.setImageResourceID(R.drawable.bernal_heights);
        parkItems.add(bernalHeights);

        ParkItem washingtonSquare = new ParkItem();
        washingtonSquare.setPrimaryKey(8);
        washingtonSquare.setName("Washington Square");
        washingtonSquare.setSize(10);
        washingtonSquare.setBusyness(10);
        washingtonSquare.setCleanliness(6);
        washingtonSquare.setFavorite(0);
        washingtonSquare.setImageResourceID(R.drawable.washington_square_683x506);
        parkItems.add(washingtonSquare);

        ParkItem pioneerPark = new ParkItem();
        pioneerPark.setPrimaryKey(9);
        pioneerPark.setName("Pioneer Park");
        pioneerPark.setSize(5);
        pioneerPark.setBusyness(7);
        pioneerPark.setCleanliness(9);
        pioneerPark.setFavorite(0);
        pioneerPark.setImageResourceID(R.drawable.washington_square_683x506);
        parkItems.add(pioneerPark);

        return parkItems;
    }

}

//        db.insert(1,"Dolores",16,10,5,0, R.drawable.dolores_park);
//        db.insert(2, "Marina Green", 74, 8, 9, 0, R.drawable.marina_green_400x533_use);
//        db.insert(3, "Alta Plaza", 12, 6, 10, 0, R.drawable.alta_plaza_683x455);
//        db.insert(4, "Golden Gate", 1017, 9, 8, 0, R.drawable.golden_gate_re_size);
//        db.insert(5, "Lands End", 45, 4, 9, 0, R.drawable.lands_end);
//        db.insert(6, "Lake Merced", 614, 7, 7, 0, R.drawable.lake_merced_586x286);
//        db.insert(7, "Bernal Heights", 26, 5, 6, 0, R.drawable.bernal_heights);
//        db.insert(8, "Washington Square", 10, 10, 6, 0, R.drawable.washington_square_683x506);

//        db.insert(9, "Pioneer ParkItem", 5, 7, 9, 0, R.drawable.pioneer_park);