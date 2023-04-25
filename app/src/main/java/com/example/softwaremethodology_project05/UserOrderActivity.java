package com.example.softwaremethodology_project05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity for user order, represents the current order.
 * @author Joshua McArdle
 */
public class UserOrderActivity extends AppCompatActivity {

    private ListView list;

    private TextView order_Total, sub_Total, sales_Tax, display_Total;

    private Button place_Order_Button;

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
        setContentView(R.layout.activity_user_order);

        list = findViewById(R.id.list);

        ArrayAdapter orderList = new ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.order.totalOrders());

        list.setAdapter(orderList);
        setTitle("Current Order");
        MainActivity.order.setTotal(roundToTwoDecimal(MainActivity.order.getTotal()));
        order_Total = findViewById(R.id.order_Total);
        sub_Total = findViewById(R.id.sub_Total);
        display_Total = findViewById(R.id.display_Total);
        sales_Tax = findViewById(R.id.sales_Tax);
        String total = "ORDER TOTAL: $" + MainActivity.order.getTotal();
        String total2 = "Total: $" + MainActivity.order.getTotal();
        String subTotal = "Sub-Total: $" + MainActivity.order.getSubTotal();
        order_Total.setText(total);
        sub_Total.setText(subTotal);
        display_Total.setText(total2);
        int taxNum = (int) ((100 * MainActivity.order.getTotal()) - (100 * MainActivity.order.getSubTotal()));
        double tax = (double) taxNum;
        if(tax != 0.0) {
            tax = tax / 100;
            tax = roundToTwoDecimal(tax);
        }
        sales_Tax.setText("Sales Tax: $" + tax);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Invoked when item is pressed in the ListView.
             * @param adapterView The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param l The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String selectedItem = (String) adapterView.getItemAtPosition(position);
                updateSubTotal(selectedItem);
                remove(position);

            }
        });

    }

    /**
     * Removes a menuitem from the order.
     * @param position, index of the item which is to be removed.
     */
    private void remove (int position) {

        MainActivity.order.remove("" + position);
        Intent intent = new Intent(this, UserOrderActivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * Updates the subtotal when an item is removed from the list.
     * @param order, the string of the order.
     */
    private void updateSubTotal(String order) {

        String[] arrOfStrings = order.split("\\$|\\s+|,");
        Double amount = Double.parseDouble(arrOfStrings[3]);
        //System.out.println(amount);
        MainActivity.order.setSubTotal(MainActivity.order.getSubTotal() - amount);

    }

    /**
     * Adds the current order to the list of all orders when the "Place Order" button is pressed.
     * @param view, the view to be passed into the method.
     */
    public void addToStoreOrders(View view) {

        ArrayList<String> order = MainActivity.order.totalOrders();
        String listOfItems = "";
        for(int i = 0; i < order.size(); i++) {

            listOfItems = listOfItems + order.get(i) + "\n";
        }
        if(MainActivity.order.getSubTotal() == 0.0) {
            finish();
            return;
        }
        listOfItems = listOfItems + "Total Amount = S" + MainActivity.order.getTotal() + "\n";
        MainActivity.storeOrders.add(listOfItems);
        MainActivity.order.clearList();
        Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Rounds a double value to two decimal places.
     * @param num, the value to be rounded.
     * @return a double value randed to two decimal places.
     */
    private double roundToTwoDecimal(double num) {

        return Math.round(num * 100.0) / 100.0;
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