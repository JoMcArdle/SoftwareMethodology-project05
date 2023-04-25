package com.example.softwaremethodology_project05;

import java.util.ArrayList;

/**
 * StoreOrder class used to represent all placed orders.
 * @author Joshua McArdle
 */
public class StoreOrder {

    private int orderNumber = 0;

    private ArrayList<String> allOrders = new ArrayList();

    /**
     * Empty constructor.
     */
    public StoreOrder() {


    }

    /**
     * Getter method, returns the order number for a store order.
     * @return orderNumber, the number of the store order.
     */
    public int getOrderNumber () {

        return orderNumber;
    }

    /**
     * Add method to add individual orders to the store order array list.
     * @param obj, order to be added.
     * @return true if order was added successfully.
     */
    public boolean add(Object obj) {

        orderNumber++;
        String order = (String) obj;
        allOrders.add(order);
        return true;
    }

    /**
     * Getter method, returns the arraylist containing all orders.
     * @return
     */
    public ArrayList<String> getArrayList() {

        return allOrders;
    }

    /**
     * Removes an individual order from the store order array list.
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {

        orderNumber--;
        allOrders.remove(Integer.parseInt(obj.toString()));
        return true;
    }
}
