package tbhova.livingspacespricecalculator;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button calculateButton;
    private EditText priceInput, dateInput;
    private TextView finalPrice;
    private DatePickerDialog.OnDateSetListener dateUpdatedPickerListener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        calculateButton = (Button)findViewById(R.id.caclulatePriceButton);
        priceInput = (EditText)findViewById(R.id.DisplayedPrice);
        dateInput = (EditText)findViewById(R.id.UpdatedDate);
        dateInput.setInputType(InputType.TYPE_NULL);
        finalPrice = (TextView)findViewById(R.id.FinalPriceValue);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String price = priceInput.getText().toString();
                final int cents = Integer.parseInt(price) * 100;

                final String date = dateInput.getText().toString();
                try {
                    DateTime updatedDate = new DateTime(dateFormat.parse(date).getTime());

                    DateTime today = new DateTime(Calendar.getInstance().getTime().getTime());

                    int weeks = Weeks.weeksBetween(updatedDate, today).getWeeks();

                    int newPriceInCents = PriceCalculator.calculatePrice(cents, weeks);
                    finalPrice.setText("$" + newPriceInCents / 100 + "." + newPriceInCents % 100);
                } catch (Exception ex) {

                }
            }
        });

        dateUpdatedPickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                dateInput.setText(dateFormat.format(newDate.getTime()));
            }
        };
        final Context mainContext = this;
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(mainContext, dateUpdatedPickerListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
