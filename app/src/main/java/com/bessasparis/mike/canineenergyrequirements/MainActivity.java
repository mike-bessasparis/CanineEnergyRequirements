package com.bessasparis.mike.canineenergyrequirements;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView DERText = (TextView) findViewById(R.id.DERtextView);

        final EditText weightText = (EditText) findViewById(R.id.editWeightText);
        weightText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                int e;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    e = calcBasicEnergyReq(Integer.parseInt(weightText.getText().toString()));
                    DERText.setText("Daily Energy Requirement is " + e + " kcal/day");
                    handled = true;
                }

                return handled;
            }
        });

    }

    public double calcRER(int weightKG) {
        return Math.pow(weightKG, .75) * 70;
    }

    public int calcBasicEnergyReq(int weightKG) {
        int e;
        //adult neutered
        e = (int) (1.6 * calcRER(weightKG));

        //adult intact
        e = (int) (1.8 * calcRER(weightKG));

        Log.i("mjb", "RER: " + e);

        return e;
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
