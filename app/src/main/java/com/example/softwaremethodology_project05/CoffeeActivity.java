package com.example.softwaremethodology_project05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Coffee coffee;

    double SHORT_SIZE_PRICE = 1.89;
    double TALL_SIZE_PRICE = 2.29;
    double GRANDE_SIZE_PRICE = 2.69;
    double VENTI_SIZE_PRICE = 3.09;
    double ADD_IN_PRICE = 0.30;
    public String sizeString;
    HashSet<String> addInsHashSet = new HashSet<>();
    public CheckBox cbSweatCream, cbFrenchVanilla, cbIrishCream, cbCaramel, cbMocha;
    String[] sizes = {"Select size", "Short", "Tall", "Grande", "Venti"};
    TextView text;
    ArrayAdapter coffeeSizeAdapter;
    Spinner coffeeSpinner;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        text = findViewById(R.id.coffeeSubtotal);

        cbSweatCream = findViewById(R.id.cbSweatCream);
        cbFrenchVanilla = findViewById(R.id.cbFrenchVanilla);
        cbIrishCream = findViewById(R.id.cbIrishCream);
        cbCaramel = findViewById(R.id.cbCaramel);
        cbMocha = findViewById(R.id.cbMocha);
        text.setText("$0.00");

        coffeeSpinner = findViewById(R.id.coffeeSizeSpinner);
        coffeeSpinner.setOnItemSelectedListener(this);
        coffeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text.setText("$0.00");
                cleanHashSet();
                unSelectCheckBoxes();
                sizeString = coffeeSpinner.getSelectedItem().toString();
                setTemporaryPrice(sizeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        coffeeSizeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSpinner.setAdapter(coffeeSizeAdapter);

    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(this, sizes[i], Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void unSelectCheckBoxes(){
        cbSweatCream.setChecked(false);
        cbFrenchVanilla.setChecked(false);
        cbIrishCream.setChecked(false);
        cbMocha.setChecked(false);
        cbCaramel.setChecked(false);
    }
    public void setTemporaryPrice(String s){
        double price;
        //String myText="";
        if(sizeString.equals("Short")){
            price = SHORT_SIZE_PRICE + addInsHashSet.size()* ADD_IN_PRICE;
            s= "$ " + df.format(price);
            text.setText(s);
            System.out.println(s);
        }else if(sizeString.equals("Tall")){
            price = TALL_SIZE_PRICE + addInsHashSet.size()* ADD_IN_PRICE;
            s= "$ " + df.format(price);
            text.setText(s);
            System.out.println(s);

        }else if(sizeString.equals("Grande")){
            price = GRANDE_SIZE_PRICE + addInsHashSet.size()* ADD_IN_PRICE;
            s= "$ " + df.format(price);
            text.setText(s);
            System.out.println(s);

        }else if(sizeString.equals("Venti")){
            price = VENTI_SIZE_PRICE + addInsHashSet.size()* ADD_IN_PRICE;
            s= "$ " + df.format(price);
            text.setText(s);
            System.out.println(s);
        }else{
            s = "$0.00";
            text.setText(s);
            System.out.println(s);
        }


    }
    public void cleanHashSet(){
       addInsHashSet.clear();
    }
    public void cheBoxClear(){
        this.cbSweatCream.setChecked(false);
        this.cbFrenchVanilla.setChecked(false);
        this.cbIrishCream.setChecked(false);
        this.cbCaramel.setChecked(false);
        this.cbMocha.setChecked(false);
    }

    public void displayAlert(Coffee coffee){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Your order is: " + coffee + " "
                + ". Would you like to add it?");
        alert.setTitle("Add to the order");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                coffeeSpinner.setSelection(0);
                cheBoxClear();
                text.setText("$0.00");
                cleanHashSet();
                unSelectCheckBoxes();
                System.out.println(coffee.getPrice());
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "order no added", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void coffeeAddToOrder(View view){
        Toast.makeText(this, "added to your order", Toast.LENGTH_SHORT).show();
        System.out.println(addInsHashSet);
        if(!sizeString.equals("Select size")){
            coffee = new Coffee();
            coffee.setQuantity(1);
            coffee.setAddIns(addInsHashSet);
            coffee.setCupSize(sizeString);
            System.out.println(coffee);
            coffee.setAddIns(addInsHashSet);
            double price = coffee.itemPrice();
            text.setText("$" + df.format(price));
            displayAlert(coffee);
            MainActivity.order.add(coffee);
            System.out.println(MainActivity.order);


        }else{
            Toast.makeText(this, "You must select a size", Toast.LENGTH_SHORT).show();
            System.out.println("else");
        }
    }
    public void checkBoxesSelections(View view){
        if(cbSweatCream.isChecked()){
            addInsHashSet.add(cbSweatCream.getText().toString());
            setTemporaryPrice(sizeString);
            System.out.println(cbSweatCream.getText().toString());
            System.out.println(addInsHashSet);
        }else if(!cbSweatCream.isChecked()){
            if(addInsHashSet.contains(cbSweatCream.getText().toString())){
                setTemporaryPrice(sizeString);
                addInsHashSet.remove(cbSweatCream.getText().toString());
                System.out.println(addInsHashSet);
            }
        }
        if(cbFrenchVanilla.isChecked()){
            addInsHashSet.add(cbFrenchVanilla.getText().toString());
            setTemporaryPrice(sizeString);
            System.out.println(cbFrenchVanilla.getText().toString());
            System.out.println(addInsHashSet);
        }
        else if(!cbFrenchVanilla.isChecked()){
            if(addInsHashSet.contains(cbFrenchVanilla.getText().toString())){
                addInsHashSet.remove(cbFrenchVanilla.getText().toString());
                setTemporaryPrice(sizeString);
                System.out.println(addInsHashSet);
            }
        }
        if(cbIrishCream.isChecked()){
            addInsHashSet.add(cbIrishCream.getText().toString());
            System.out.println(cbIrishCream.getText().toString());
            setTemporaryPrice(sizeString);
            System.out.println(addInsHashSet);
        }else if(!cbIrishCream.isChecked()){
            if(addInsHashSet.contains(cbIrishCream.getText().toString())){
                addInsHashSet.remove(cbIrishCream.getText().toString());
                setTemporaryPrice(sizeString);
                System.out.println(addInsHashSet);
            }
        }
        if(cbCaramel.isChecked()){
            addInsHashSet.add(cbCaramel.getText().toString());
            setTemporaryPrice(sizeString);
            System.out.println(cbCaramel.getText().toString());
            System.out.println(addInsHashSet);
        }
        else if(!cbCaramel.isChecked()){
            if(addInsHashSet.contains(cbCaramel.getText().toString())){
                addInsHashSet.remove(cbCaramel.getText().toString());
                setTemporaryPrice(sizeString);
                System.out.println(addInsHashSet);
            }
        }if(cbMocha.isChecked()){
            addInsHashSet.add(cbMocha.getText().toString());
            setTemporaryPrice(sizeString);
            System.out.println(cbMocha.getText().toString());
            System.out.println(addInsHashSet);
        }else if(!cbMocha.isChecked()){
            if(addInsHashSet.contains(cbMocha.getText().toString())){
                addInsHashSet.remove(cbMocha.getText().toString());
                setTemporaryPrice(sizeString);
                System.out.println(addInsHashSet);
            }
        }

    }
}