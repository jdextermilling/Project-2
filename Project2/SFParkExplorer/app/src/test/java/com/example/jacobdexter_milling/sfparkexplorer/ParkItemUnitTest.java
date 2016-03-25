package com.example.jacobdexter_milling.sfparkexplorer;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by JacobDexter-Milling on 3/24/16.
 */
public class ParkItemUnitTest {


    /**
     * This is a test of the getPrimaryKey method implemented in ParkItem class.
     */
    @Test
    public void testGetPrimaryKey(){
        ParkItem wtfPark = new ParkItem();
        wtfPark.setPrimaryKey(17);

        int expected = 17;
        int actual = wtfPark.getPrimaryKey();

        Assert.assertEquals(expected, actual);
    }

    /**
     * This is a test of the getName method implemented in ParkItem class.
     */
    @Test
    public void testGetName() {
        ParkItem mysteryPark = new ParkItem();
        mysteryPark.setName("Mystery");

        String expected = "Mystery";
        String actual = mysteryPark.getName();

        Assert.assertEquals(expected, actual);
    }

    /**
     * This is a test of the get getSize method implemented in the ParkItem class.
     */
    @Test
    public void testGetSize(){
        ParkItem randoPark = new ParkItem();
        randoPark.setSize(77);

        int expected = 77;
        int actual = randoPark.getSize();

        Assert.assertEquals(expected, actual);
    }

    /**
     * This is a test of the get getBusyness method implemented in the ParkItem class.
     */
    @Test
    public void testGetBusyness(){
        ParkItem randoPark = new ParkItem();
        randoPark.setBusyness(4);

        int expected = 4;
        int actual = randoPark.getBusyness();

        Assert.assertEquals(expected, actual);
    }

    /**
     * This is a test of the get getCleanliness method implemented in the ParkItem class.
     */
    @Test
    public void testGetCleanliness(){
        ParkItem randoPark = new ParkItem();
        randoPark.setCleanliness(8);

        int expected = 8;
        int actual = randoPark.getCleanliness();

        Assert.assertEquals(expected, actual);
    }





}


