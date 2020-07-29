package com.example.fatdetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fatdetector.importantStuff.CustomFatViewAdapter;
import com.example.fatdetector.importantStuff.DataBaseHelper;
import com.example.fatdetector.importantStuff.Fat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private RecyclerView recyclerView;
    private ArrayList<Fat> fatList;
    MainActivity mainActivity;
    Button addFat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        mainActivity = this;

        DataBaseHelper dbHelper = new DataBaseHelper (this);
        mDatabase = dbHelper.getWritableDatabase ();

        recyclerView = (RecyclerView) findViewById(R.id.fatList);
        addFat = (Button) findViewById ( R.id.addDay );

        //mDatabase.execSQL ( "DROP TABLE IF EXISTS fatlist" );
        mDatabase.execSQL ( "CREATE TABLE IF NOT EXISTS fatlist (id INTEGER PRIMARY KEY, whatfat VARCHAR,howmuchfat VARCHAR,whenyoueat VARCHAR,desserteat INTEGER,fastfoodeat INTEGER,snackeat INTEGER )" );
        /*mDatabase.execSQL ( "INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES('tost','200kalori','7/28/2020','1','1','1')" );
        mDatabase.execSQL ( "INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES('tost2','300kalori','7/28/2020','1','0','1')" );
        mDatabase.execSQL ( "INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES('tost3','400kalori','7/28/2020','0','1','1')" );
        mDatabase.execSQL ( "INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES('tost4','500kalori','7/28/2020','1','0','1')" );
        mDatabase.execSQL ( "INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES('tost5','600kalori','7/28/2020','1','1','0')" );*/




        adapterCaller ();



    }
    public void adapterCaller()
    {
        CustomFatViewAdapter productAdapter = new CustomFatViewAdapter (this, getAllItems ());
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void addDay(View v)
    {
        Intent intent = new Intent ( getApplicationContext (),DayInputActivity.class );
        startActivity ( intent );
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                Fat.FatEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Fat.FatEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
