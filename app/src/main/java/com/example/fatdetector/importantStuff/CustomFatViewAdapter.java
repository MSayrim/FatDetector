package com.example.fatdetector.importantStuff;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.fatdetector.importantStuff.CustomFatViewAdapter;
import com.example.fatdetector.importantStuff.DataBaseHelper;
import com.example.fatdetector.importantStuff.Fat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatdetector.R;

import java.util.ArrayList;

public class CustomFatViewAdapter extends RecyclerView.Adapter<CustomFatViewAdapter.FatViewHolder> {
    Cursor mFats;
    LayoutInflater inflater;


    public CustomFatViewAdapter(Context context, Cursor mFats) {
        inflater = LayoutInflater.from(context);
        this.mFats =  mFats;
    }


    @Override
    public FatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fat_recycleview_activity, parent, false);
        FatViewHolder holder = new FatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FatViewHolder holder, int position) {
        if(!mFats.moveToPosition ( position )){
            return;
        }
        String name = mFats.getString ( mFats.getColumnIndex ( Fat.FatEntry.COLUMN_NAME ) );

        String amount = mFats.getString ( mFats.getColumnIndex ( Fat.FatEntry.COLUMN_AMOUNT ) );

        String date = mFats.getString ( mFats.getColumnIndex ( Fat.FatEntry.COLUMN_TIMESTAMP ) );

        int a = mFats.getInt (mFats.getColumnIndex ( Fat.FatEntry.COLUMN_LATESNACK));
        int b = mFats.getInt (mFats.getColumnIndex ( Fat.FatEntry.COLUMN_DESSERT));
        int c = mFats.getInt (mFats.getColumnIndex ( Fat.FatEntry.COLUMN_FASTFOOD));

        if ( a > 0)
        {
            holder.snack.setChecked ( true );
        }
        else {
            holder.snack.setChecked ( false );
        }
        if (c>0)
        {
            holder.fastFood.setChecked ( true );
        }
        else {
            holder.fastFood.setChecked ( false );
        }
        if (b>0)
        {
            holder.dessert.setChecked ( true );
        }
        else {
            holder.dessert.setChecked ( false );
        }

        holder.fatName.setText ( name );
        holder.fatAmount.setText ( amount );
        holder.fatDate.setText ( date );
    }

    @Override
    public int getItemCount() {
        return mFats.getCount ();
    }

    public void swapCursor(Cursor newCursor) {
        if (mFats != null) {
            mFats.close();
        }
        mFats= newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    class FatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fatName, fatDate,fatAmount;
        CheckBox snack,dessert,fastFood;

        public FatViewHolder(View itemView) {
            super(itemView);
            fatName = (TextView) itemView.findViewById(R.id.fatNameView);
            fatDate = (TextView) itemView.findViewById(R.id.dateView);
            fatAmount = (TextView) itemView.findViewById ( R.id.fatView );
            snack = (CheckBox) itemView.findViewById ( R.id.lateSnack );
            dessert = (CheckBox) itemView.findViewById ( R.id.dessert );
            fastFood = (CheckBox) itemView.findViewById ( R.id.fastFood );


        }



        @Override
        public void onClick(View v) {


        }


    }
}