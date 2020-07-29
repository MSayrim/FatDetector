package com.example.fatdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fatdetector.MainActivity;
import com.example.fatdetector.importantStuff.DataBaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DayInputActivity extends AppCompatActivity {

    TextView foodName;
    TextView foodFat;
    EditText foodDate;
    CheckBox fastFood;
    CheckBox dessert;
    CheckBox snack;
    Calendar myCalendar;

    private SQLiteDatabase mDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_day_input );


        DataBaseHelper dbHelper = new DataBaseHelper (this);
        mDatabase = dbHelper.getWritableDatabase ();

        mDatabase.execSQL ( "CREATE TABLE IF NOT EXISTS fatlist (id INTEGER PRIMARY KEY, whatfat VARCHAR,howmuchfat VARCHAR,whenyoueat VARCHAR,desserteat INTEGER,fastfoodeat INTEGER,snackeat INTEGER )" );
        foodName = (TextView) findViewById ( R.id.inputFoodNameText );
        foodFat = (TextView) findViewById ( R.id.inputFatText );
        foodDate = (EditText) findViewById ( R.id.inputFoodDateText );
        fastFood = (CheckBox) findViewById ( R.id.inputFastfood );
        dessert = (CheckBox) findViewById ( R.id.inputDessert );
        snack = (CheckBox) findViewById ( R.id.inputSnack );

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        foodDate.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DayInputActivity.this,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }



    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat (myFormat, Locale.GERMANY);

        foodDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void ekle (View v)
    {
        String tempFoodName = foodName.getText ().toString ();
        String tempFoodFat = foodFat.getText ().toString ();
        String tempFoodDate = foodDate.getText ().toString ();

        int tempFastFood = 1;
        int tempDessert = 1;
        int tempSnack = 1;
        if(fastFood.isChecked ())
        {
             tempFastFood = 1;
        }
        else {
            tempFastFood = 0;
        }

        if(dessert.isChecked ())
        {
            tempDessert = 1;
        }
        else {
            tempDessert = 0;
        }

        if(snack.isChecked ())
        {
            tempSnack = 1;
        }
        else {
            tempSnack = 0;
        }


        String sqlString ="INSERT INTO fatlist (whatfat,howmuchfat,whenyoueat,desserteat,fastfoodeat,snackeat) VALUES(?,?,?,?,?,?)";
        SQLiteStatement sqLiteStatement = mDatabase.compileStatement ( sqlString );
        sqLiteStatement.bindString ( 1,tempFoodName );
        sqLiteStatement.bindString ( 2,tempFoodFat );
        sqLiteStatement.bindString ( 3,tempFoodDate );
        sqLiteStatement.bindDouble ( 4,tempDessert );
        sqLiteStatement.bindDouble ( 5,tempFastFood );
        sqLiteStatement.bindDouble ( 6,tempSnack );
        sqLiteStatement.execute ();
        Intent intent = new Intent ( getApplicationContext (),MainActivity.class );
        intent.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity ( intent );
        finish ();
    }
}
