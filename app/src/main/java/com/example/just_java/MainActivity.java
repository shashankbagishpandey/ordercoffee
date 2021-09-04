package com.example.just_java;
/**

 IMPORTANT: Make sure you are using the correct package name.
 This example uses the package name:
 package com.example.android.justjava
 If you get an error when copying this code into Android studio, update it to match teh package name found
 in the project's AndroidManifest.xml file.
 **/
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**

     This method is called when the order button is clicked.
     */ int quantity=0;

    int wc=1;
    int choc=2;
    public void submitOrder(View view) {
        CheckBox checkBox=(CheckBox) findViewById(R.id.check_box);
        CheckBox checkBox1=(CheckBox) findViewById(R.id.check1_box);
        boolean haswhippedcreame=checkBox.isChecked();
        boolean  choclate=checkBox1.isChecked();

        EditText namefield=(EditText) findViewById(R.id.name_field);
        String name=namefield.getText().toString();
        Log.v("MainAcivity","name"+name);
        // Log.v("MainActivity","has checked"+haswhippedcreame);
        int price=calculatePrice( haswhippedcreame,choclate);
        String msg=createOrderSummary( name,price,haswhippedcreame,choclate);
        String priceMessage =msg;



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"admin@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }
    /**
     * Calculates the price of the order.
     *
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean haswhippedcreame, boolean choclate) {
        int percup=5;
        if (choclate){
            percup=percup+choc;
        }
        if (haswhippedcreame){
            percup=percup+wc;
        }

        return quantity * percup;
    }
    private String createOrderSummary( String name, int price,boolean addwhippedcreame,boolean choclate ){

        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage+= "\n" + getString(R.string.order_summary_whipped_cream, addwhippedcreame);
        priceMessage+= "\n" + getString(R.string.order_summary_chocolate, choclate);
        priceMessage+=  "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage+=  "\n" +"Total: $"+price;
        priceMessage+= "\n" + getString (R.string.thank_you);
        return priceMessage;


        // return "name:"+name+"\n"+"Add whipped Creame?"+addwhippedcreame+"\n"+"Add choclate?"+choclate+"\n"+"Quantity:"+quantity+"\n"+"Total:"+price+"\n"+"Thankyou";


    }


    /**

     This method is called when the + button is clicked.
     */
    public void increment(View view) {

        quantity++;
        display(quantity);


    }
    /**

     This method is called when the - button is clicked.
     */
    public void decrement(View view) {

        quantity--;
        display(quantity);


    }
    /**

     This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }







}