package com.bessasparis.mike.canineenergyrequirements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updateDER(View view) {

        //input field is weight in pounds
        double weightInPounds;

        //holds converted weight from punds to kilograms
        double weightKG;

        EditText weightText = (EditText) findViewById(R.id.editWeightText);
        try {
            weightInPounds = Integer.parseInt(weightText.getText().toString());
            weightKG = weightInPounds / 2.2; //convert from pounds to kilograms
        }
        catch (NumberFormatException e) {
            weightKG = 0;
        }

        TextView NADERText = (TextView) findViewById(R.id.NeuteredAdultDERtextView);
        TextView IADERText = (TextView) findViewById(R.id.IntactAdultDERtextView);
        NADERText.setText("Neutered: " +
                calcAdultEnergyReq(weightKG, 0) +
                " kcal");
        IADERText.setText("Intact: " +
                calcAdultEnergyReq(weightKG, 1) +
                " kcal");

        TextView UpTo42DERText = (TextView) findViewById(R.id.UpTo42DaysDERtextView);
        TextView After42DERText = (TextView) findViewById(R.id.After42DaysDERtextView);
        UpTo42DERText.setText("1-42 days: " +
                calcAdultEnergyReq(weightKG, 2) +
                " kcal");
        After42DERText.setText("43-63 days: " +
                calcAdultEnergyReq(weightKG, 3) +
                " kcal");

        TextView OnePupDERText = (TextView) findViewById(R.id.OnePupDERtextView);
        TextView TwoPupDERText = (TextView) findViewById(R.id.TwoPupDERtextView);
        TextView ThreeFourPupDERText = (TextView) findViewById(R.id.ThreeFourPupDERtextView);
        TextView FiveSixPupDERText = (TextView) findViewById(R.id.FiveSixPupDERtextView);
        TextView SevenEightPupDERText = (TextView) findViewById(R.id.SevenEightPupDERtextView);
        TextView NineOrMorePupDERText = (TextView) findViewById(R.id.NineOrMorePupDERtextView);
        OnePupDERText.setText("1 pup: " +
                calcAdultEnergyReq(weightKG, 4) +
                " kcal");
        TwoPupDERText.setText("2 pups: " +
                calcAdultEnergyReq(weightKG, 5) +
                " kcal");
        ThreeFourPupDERText.setText("3-4 pups: " +
                calcAdultEnergyReq(weightKG, 6) +
                " kcal");
        FiveSixPupDERText.setText("5-6 pups: " +
                calcAdultEnergyReq(weightKG, 7) +
                " kcal");
        SevenEightPupDERText.setText("7-8 pups: " +
                calcAdultEnergyReq(weightKG, 8) +
                " kcal");
        NineOrMorePupDERText.setText("9 or more pups: " +
                calcAdultEnergyReq(weightKG, 9) +
                " kcal");


    }

    private double calcRER(double weightKG) {
        return Math.pow(weightKG, .75) * 70;
    }

    //repro, neutered==0, intact==1
    //gestation, < 42 days==2, >42 days==3
    //lactation, 1 pup==4, 2pup==5, 3-4pup=6, 5-6pup==7, 7-8pup==8, >9==9
    private int calcAdultEnergyReq(double weightKG, int type) {
        int e = 0;
        switch (type) {
            //adult neutered
            case 0: e = (int) (1.6 * calcRER(weightKG));
                    break;
            //adult intact
            case 1: e = (int) (1.8 * calcRER(weightKG));
                    break;
            //gestation < 42 days
            case 2: e = (int) (1.8 * calcRER(weightKG));
                break;
            //gestation > 42 days
            case 3: e = (int) (3 * calcRER(weightKG));
                break;
            //lactation 1 pup
            case 4: e = (int) (3 * calcRER(weightKG));
                break;
            //lactation 2 pup
            case 5: e = (int) (3.5 * calcRER(weightKG));
                break;
            //lactation 3-4 pup
            case 6: e = (int) (4 * calcRER(weightKG));
                break;
            //lactation 5-6 pup
            case 7: e = (int) (5 * calcRER(weightKG));
                break;
            //lactation 7-8 pup
            case 8: e = (int) (5.5 * calcRER(weightKG));
                break;
            //lactation >9 pup
            case 9: e = (int) (6 * calcRER(weightKG));
                break;
        }

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

        switch (item.getItemId()) {
            case R.id.action_settings: {
                return true;
            }
            case R.id.action_about: {
                Intent intent = new Intent(this, ActionsAbout.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
