package com.example.softwaremethodology_project05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Coffee coffee;
    public String sizeString;
    HashSet<String> addInsHashSet = new HashSet<>();
    public CheckBox cbSweatCream, cbFrenchVanilla, cbIrishCream, cbCaramel, cbMocha;
    String[] sizes = {"Select size", "Short", "Tall", "Grande", "Venti"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        cbSweatCream = findViewById(R.id.cbSweatCream);
        cbFrenchVanilla = findViewById(R.id.cbFrenchVanilla);
        cbIrishCream = findViewById(R.id.cbIrishCream);
        cbCaramel = findViewById(R.id.cbCaramel);
        cbMocha = findViewById(R.id.cbMocha);

        Spinner coffeeSpinner = findViewById(R.id.coffeeSizeSpinner);
        coffeeSpinner.setOnItemSelectedListener(this);
        coffeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                sizeString = coffeeSpinner.getSelectedItem().toString();
                System.out.println(sizeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter coffeeSizeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);
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
    public void cleanHashSet(){
        for(String s: addInsHashSet){
            addInsHashSet.remove(s);
        }
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
            cleanHashSet();
            coffee.setAddIns(addInsHashSet);
        }
    }
    public void checkBoxesSelections(View view){
        if(cbSweatCream.isChecked()){
            addInsHashSet.add(cbSweatCream.getText().toString());
            System.out.println(cbSweatCream.getText().toString());
            System.out.println(addInsHashSet);
        }else if(!cbSweatCream.isChecked()){
            if(addInsHashSet.contains(cbSweatCream.getText().toString())){
                addInsHashSet.remove(cbSweatCream.getText().toString());
                System.out.println(addInsHashSet);
            }
        }
        if(cbFrenchVanilla.isChecked()){
            addInsHashSet.add(cbFrenchVanilla.getText().toString());
            System.out.println(cbFrenchVanilla.getText().toString());
            System.out.println(addInsHashSet);
        }
        else if(!cbFrenchVanilla.isChecked()){
            if(addInsHashSet.contains(cbFrenchVanilla.getText().toString())){
                addInsHashSet.remove(cbFrenchVanilla.getText().toString());
                System.out.println(addInsHashSet);
            }
        }
        if(cbIrishCream.isChecked()){
            addInsHashSet.add(cbIrishCream.getText().toString());
            System.out.println(cbIrishCream.getText().toString());
            System.out.println(addInsHashSet);
        }else if(!cbIrishCream.isChecked()){
            if(addInsHashSet.contains(cbIrishCream.getText().toString())){
                addInsHashSet.remove(cbIrishCream.getText().toString());
                System.out.println(addInsHashSet);
            }
        }
        if(cbCaramel.isChecked()){
            addInsHashSet.add(cbCaramel.getText().toString());
            System.out.println(cbCaramel.getText().toString());
            System.out.println(addInsHashSet);
        }
        else if(!cbCaramel.isChecked()){
            if(addInsHashSet.contains(cbCaramel.getText().toString())){
                addInsHashSet.remove(cbCaramel.getText().toString());
                System.out.println(addInsHashSet);
            }
        }if(cbMocha.isChecked()){
            addInsHashSet.add(cbMocha.getText().toString());
            System.out.println(cbMocha.getText().toString());
            System.out.println(addInsHashSet);
        }else if(!cbMocha.isChecked()){
            if(addInsHashSet.contains(cbMocha.getText().toString())){
                addInsHashSet.remove(cbMocha.getText().toString());
                System.out.println(addInsHashSet);
            }
        }

    }
}