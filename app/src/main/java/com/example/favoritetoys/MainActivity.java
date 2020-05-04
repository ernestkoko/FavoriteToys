package com.example.favoritetoys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //
    public  void submitOrder(View view){
        AppCompatCheckBox chocolate = findViewById(R.id.chocolate_id);
        boolean hasChocolate = chocolate.isChecked();

        AppCompatCheckBox cream = findViewById(R.id.check_box);
        boolean hasCream = cream.isChecked();



        int price = calculatePrice(hasCream, hasChocolate);
        EditText name1 = findViewById(R.id.name_field);
        String name = name1.getText().toString();
        String summary = createOrderSummary(name ,price, hasCream, hasChocolate);


//        TextView priceTextView = findViewById(R.id.price_textView);
//        priceTextView.setText(summary);
//        displayPrice(numberOfCoffees * 5);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //  display(numberOfCoffee);


    }
    private String createOrderSummary(String name, int number, boolean hasCream, boolean hasChocolate){


        String message = getString(R.string.order_summary_name, name);
        message =message.concat( "\n Add whisper cream?: " + hasCream);
        message = message.concat( "\nAdd Chocolate?: " + hasChocolate);
        message = message.concat( "\nQuantity: " + numberOfCoffees);
        message = message.concat( "\n Total: " + number);
        message = message.concat( "\n" + getString(R.string.thank_you));


        return message;
    }

    private void displayQuantity(int number) {
        TextView textView = findViewById(R.id.quantity_text_view);
        textView.setText("" + number);
    }

//    private void displayPrice(int price){
//        TextView priceTextView = findViewById(R.id.price_textView);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
//    }

    /** the increment button triggers this method
     * @parm view takes in the view
     *
     */
    public void increment(View view){
        numberOfCoffees = numberOfCoffees + 1;
        displayQuantity(numberOfCoffees);
        int quantity = 1;

    }
    /** the decrement button triggers this method
     * @parm view takes in the view
     *
     */
    public void decrement(View view){

        if (numberOfCoffees > 1){
        numberOfCoffees = numberOfCoffees -1;
        }

        if (numberOfCoffees ==0){
            Toast.makeText(this, "Please You can't order negative number of coffee",
                    Toast.LENGTH_LONG).show();
        }
        displayQuantity(numberOfCoffees);
        //int quantity = 1;

    }
    /* calculate the price
    @param addWhipserCream is whether or not the user wants whisper cream topping
    @param addChocolate is to know whether the user wants chocolate or not
    *
    * */
    private int calculatePrice(boolean addWhisperCream, boolean addChocolate){
        int basePrice = 5;
        if (addWhisperCream){
            //adds 1 to base price when user wants cream
            basePrice += 1;
        }
        if (addChocolate){
            //adds 2 to price when user wants chocolate
            basePrice += 2;
        }


    return  numberOfCoffees * basePrice;
    }

}
