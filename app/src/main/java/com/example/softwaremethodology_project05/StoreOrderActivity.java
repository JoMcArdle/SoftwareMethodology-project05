package com.example.softwaremethodology_project05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Activity for the store order, represents all orders that have been placed.
 * @author Joshua McArdle
 */
public class StoreOrderActivity extends AppCompatActivity {

    private ListView storeList;

    /**
     * Creates and displays the view when the activity is invoked.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_activity);
        storeList = findViewById(R.id.storeList);
        ArrayAdapter<String> StoreList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrders.getArrayList());
        storeList.setAdapter(StoreList);
        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                remove(position);

            }
        });
    }

    /**
     * Removes orders from the ListView.
     * @param position, index number of the order we want to remove from the ListView.
     */
    public void remove (int position) {

        MainActivity.storeOrders.remove("" + position);
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Called when an activity becomes visible to the user.
     */
    @Override
    protected void onStart(){
        super.onStart();
    }

    /**
     * Called just before the user starts interacting with the application.
     */
    @Override
    protected void onResume(){
        super.onResume();
    }
}