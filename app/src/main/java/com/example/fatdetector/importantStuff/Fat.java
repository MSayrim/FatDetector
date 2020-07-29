package com.example.fatdetector.importantStuff;

import android.provider.BaseColumns;

public class Fat {
    private String whatFat;
    private String howMuchFat;
    private String whenYouEat;
    private String dessertEat;
    private String snackEat;
    private String fastFoodEat;


    public Fat(String howMuchFat, String whenYouEat,String whatFat,String dessertEat,String snackEat,String fastFoodEat) {
        this.howMuchFat = howMuchFat;
        this.whenYouEat = whenYouEat;
        this.whatFat = whatFat;
        this.dessertEat =dessertEat;
        this.snackEat = snackEat;
        this.fastFoodEat = fastFoodEat;
    }

    public String getDessertEat() {
        return dessertEat;
    }

    public void setDessertEat(String dessertEat) {
        this.dessertEat = dessertEat;
    }

    public String getSnackEat() {
        return snackEat;
    }

    public void setSnackEat(String snackEat) {
        this.snackEat = snackEat;
    }

    public String getFastFoodEat() {
        return fastFoodEat;
    }

    public void setFastFoodEat(String fastFoodEat) {
        this.fastFoodEat = fastFoodEat;
    }

    public String getWhatFat() {
        return whatFat;
    }

    public void setWhatFat(String whatFat) {
        this.whatFat = whatFat;
    }

    public String getHowMuchFat() {
        return howMuchFat;
    }

    public void setHowMuchFat(String howMuchFat) {
        this.howMuchFat = howMuchFat;
    }

    public String getWhenYouEat() {
        return whenYouEat;
    }

    public void setWhenYouEat(String whenYouEat) {
        this.whenYouEat = whenYouEat;
    }


    public static final class FatEntry implements BaseColumns {
        public static final String TABLE_NAME = "FatList";
        public static final String COLUMN_NAME = "whatfat";
        public static final String COLUMN_AMOUNT = "howmuchfat";
        public static final String COLUMN_TIMESTAMP = "whenyoueat";
        public static final String COLUMN_DESSERT = "desserteat";
        public static final String COLUMN_FASTFOOD = "fastfoodeat";
        public static final String COLUMN_LATESNACK = "snackeat";
    }
}