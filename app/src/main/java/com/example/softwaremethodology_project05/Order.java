package com.example.softwaremethodology_project05;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class Order implements Serializable {
    private static int count;
    private int orderNumber;
    private double subtotal = 0.0;
    private double total = 0.0;
    private ArrayList<MenuItem> itemsList = new ArrayList<>();
    private static final double SALES_TAX = .0625;
    private static final double HUNDRED = 100.0; //used for rounding to two decimal places



    /**
     * Empty constructor.
     */
    public Order() {
        this.orderNumber = ++Order.count;
    }

    /**
     * Getter method, gets the total price without sales tax.
     * @return subtotal, the price of items without tax.
     */
    public double getSubTotal() {
        return this.subtotal;
    }

    /**
     * Setter method, sets the price of an order without sales tax.
     * @param subtotal, the price of the items without tax that is to be set.
     */
    public void setSubTotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString(){
        String result ="";
        for(MenuItem m: itemsList){
            result+= itemsList.toString();
        }
        return result;
    }
    public String displayInfo(Object obj) {
        if (obj instanceof Coffee) {
            MenuItem coffee = (Coffee) obj;
            return coffee.toString();
        }
        else if (obj instanceof YeastDonut) {
            MenuItem yeastDonut = (YeastDonut) obj;
            return yeastDonut.toString();
        }
        else if (obj instanceof CakeDonut) {
            MenuItem cakeDonut = (CakeDonut) obj;
            return cakeDonut.toString();
        }
        else {
            MenuItem donutHole = (DonutHole) obj;
            return donutHole.toString();
        }
    }

    /**
     * Calculates the total of all menu items with tax included.
     * @return total, the total amount for all menu items with tax included.
     */
    public double getTotal() {
        this.total = subtotal + (subtotal * SALES_TAX);
        this.total = Math.round(total * HUNDRED) / HUNDRED;
        return this.total;
    }

    /**
     * Getter method, sets the total for the order to the specified amount.
     * @param total, the amount of the order to be set
     */
    public void setTotal(double total) {

        this.total = total;
    }

    /**
     * Adds a menu item to the list.
     * @param item, the item to be added to the list.
     */
    public void add(MenuItem item) {

        orderNumber++;
        itemsList.add(item);
    }

    /**
     * Removes a menu item from the list.
     * @param obj, the item to be removed from the list.
     * @return false if the orderNumber is less than 0 and true otherwise.
     */
    public boolean remove(Object obj) {

        if(orderNumber < 0) {
            return false;
        }
        orderNumber--;
        itemsList.remove(Integer.parseInt(obj.toString()));;
        return true;
    }

    /**
     * Places an order from the basket into a list of total orders.
     * @return orders, the list of all placed orders.
     */
    public ArrayList<String> totalOrders() {
        ArrayList<String> orders = new ArrayList<>();
        for(int i = 0; i < itemsList.size(); i++) {
            orders.add(displayInfo(itemsList.get(i)));
            //orders.add(itemsList.get(i).toString());
        }
        return orders;
    }

    /**
     * Removes all menu items from the basket.
     */
    public void clearList() {

        itemsList.clear();
        this.subtotal = 0.0;
    }



}