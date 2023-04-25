package com.example.softwaremethodology_project05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static Order order = new Order();
    public static StoreOrder storeOrders = new StoreOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    public void goToDonutsView(View view){
        Intent intent = new Intent(this, DonutsActivity.class);
        startActivity(intent);
    }
    public void goToCoffeeView(View view){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    public void goToUserOrderView(View view){
        Intent intent = new Intent(this, UserOrderActivity.class);
        startActivity(intent);
    }
    public void goToStoreOrderView(View view){
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }

}